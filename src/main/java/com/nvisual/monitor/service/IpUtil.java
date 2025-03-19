package com.nvisual.monitor.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.nvisual.monitor.model.IpNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class IpUtil {
    private final ConfigService configService;
    public IpUtil(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * 在main方法里面可以测试下 
     * 主要功能都在main方法里，需要什么自己找 
     */
    public static void main(String[] args) {
        final List<String> strings = parseRange3("192.168.100.1-254");
        for (String s :
                strings) {
            System.err.println(s);
        }

    }

    /**
     * 功能：判断一个IP是不是在一个网段下的
     * 格式：isInRange("192.168.8.3", "192.168.9.10/22"); 
     */
    public static boolean isInRange(String ip, String cidr) {
        String[] ips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(ips[0]) << 24)
                | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
        int mask = 0xFFFFFFFF << (32 - type);
        String cidrIp = cidr.replaceAll("/.*", "");
        String[] cidrIps = cidrIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
                | (Integer.parseInt(cidrIps[1]) << 16)
                | (Integer.parseInt(cidrIps[2]) << 8)
                | Integer.parseInt(cidrIps[3]);

        return (ipAddr & mask) == (cidrIpAddr & mask);
    }

    /**
     * 功能：验证子网格式
     * 格式：isValidIPAddress("192.168.9.10/22");
     */
    // 正则表达式，用于匹配IP地址和子网掩码
    private static final String IP_ADDRESS_REGEX =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final String SUBNET_MASK_REGEX = "^([0-9]|[1-2][0-9]|3[0-2])$";

    public static boolean isValidSubnet(String subnet) {
        if (subnet == null || !subnet.contains("/")) {
            return false;
        }

        String[] parts = subnet.split("/");
        if (parts.length != 2) {
            return false;
        }

        String ipAddress = parts[0];
        String subnetMask = parts[1];

        return isValidIPAddress(ipAddress) && isValidSubnetMask(subnetMask);
    }

    private static boolean isValidIPAddress(String ipAddress) {
        Pattern pattern = Pattern.compile(IP_ADDRESS_REGEX);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    private static boolean isValidSubnetMask(String subnetMask) {
        Pattern pattern = Pattern.compile(SUBNET_MASK_REGEX);
        Matcher matcher = pattern.matcher(subnetMask);
        return matcher.matches();
    }
    /**
     * 功能：根据IP和位数返回该IP网段的所有IP 
     * 格式：parseIpMaskRange("192.192.192.1.", "23") 
     */
    public static List<String> parseIpMaskRange(String ip,String mask){
        List<String> list=new ArrayList<>();
        if ("32".equals(mask)) {
            list.add(ip);
        }else{
            String startIp=getBeginIpStr(ip, mask);
            String endIp=getEndIpStr(ip, mask);
            if (!"31".equals(mask)) {
                String subStart=startIp.split("\\.")[0]+"."+startIp.split("\\.")[1]+"."+startIp.split("\\.")[2]+".";
                String subEnd=endIp.split("\\.")[0]+"."+endIp.split("\\.")[1]+"."+endIp.split("\\.")[2]+".";
                startIp=subStart+(Integer.parseInt(startIp.split("\\.")[3])+1);
                endIp=subEnd+(Integer.parseInt(endIp.split("\\.")[3])-1);
            }
            list=parseIpRange(startIp, endIp);
        }
        return list;
    }

    /**
     * 功能：根据位数返回IP总数 
     * 格式：parseIpMaskRange("192.192.192.1", "23") 
     */
    public static int getIpCount(String mask) {
        return BigDecimal.valueOf(Math.pow(2, 32 - Integer.parseInt(mask))).setScale(0, BigDecimal.ROUND_DOWN).intValue();//IP总数，去小数点  
    }

    /**
     * 功能：根据位数返回IP总数 
     * 格式：isIP("192.192.192.1") 
     */
    public static boolean isIP(String str) {
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    public static List<String> parseIpRange(String ipfrom, String ipto) {
        List<String> ips = new ArrayList<String>();
        String[] ipfromd = ipfrom.split("\\.");
        String[] iptod = ipto.split("\\.");
        int[] int_ipf = new int[4];
        int[] int_ipt = new int[4];
        for (int i = 0; i < 4; i++) {
            int_ipf[i] = Integer.parseInt(ipfromd[i]);
            int_ipt[i] = Integer.parseInt(iptod[i]);
        }
        for (int A = int_ipf[0]; A <= int_ipt[0]; A++) {
            for (int B = (A == int_ipf[0] ? int_ipf[1] : 0); B <= (A == int_ipt[0] ? int_ipt[1]
                    : 255); B++) {
                for (int C = (B == int_ipf[1] ? int_ipf[2] : 0); C <= (B == int_ipt[1] ? int_ipt[2]
                        : 255); C++) {
                    for (int D = (C == int_ipf[2] ? int_ipf[3] : 0); D <= (C == int_ipt[2] ? int_ipt[3]
                            : 255); D++) {
                        ips.add(A + "." + B + "." + C + "." + D);
                    }
                }
            }
        }
        return ips;
    }

    /**
     * 把long类型的Ip转为一般Ip类型：xx.xx.xx.xx 
     *
     * @param ip
     * @return
     */
    public static String getIpFromLong(Long ip)
    {
        String s1 = String.valueOf((ip & 4278190080L) / 16777216L);
        String s2 = String.valueOf((ip & 16711680L) / 65536L);
        String s3 = String.valueOf((ip & 65280L) / 256L);
        String s4 = String.valueOf(ip & 255L);
        return s1 + "." + s2 + "." + s3 + "." + s4;
    }
    /**
     * 把xx.xx.xx.xx类型的转为long类型的 
     *
     * @param ip
     * @return
     */
    public static Long getIpFromString(String ip)
    {
        Long ipLong = 0L;
        String ipTemp = ip;
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf('.')));
        ipTemp = ipTemp.substring(ipTemp.indexOf('.') + 1, ipTemp.length());
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf('.')));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf('.')));
        ipTemp = ipTemp.substring(ipTemp.indexOf('.') + 1, ipTemp.length());
        ipLong = ipLong * 256 + Long.parseLong(ipTemp);
        return ipLong;
    }
    /**
     * 根据掩码位获取掩码 
     *
     * @param maskBit
     *            掩码位数，如"28"、"30" 
     * @return
     */
    public static String getMaskByMaskBit(String maskBit)
    {
        return "".equals(maskBit) ? "error, maskBit is null !" : getMaskMap(maskBit);
    }

    /**
     * 根据 ip/掩码位 计算IP段的起始IP 如 IP串 218.240.38.69/30 
     *
     * @param ip
     *            给定的IP，如218.240.38.69 
     * @param maskBit
     *            给定的掩码位，如30 
     * @return 起始IP的字符串表示
     */
    public static String getBeginIpStr(String ip, String maskBit)
    {
        return getIpFromLong(getBeginIpLong(ip, maskBit));
    }
    /**
     * 根据 ip/掩码位 计算IP段的起始IP 如 IP串 218.240.38.69/30 
     *
     * @param ip
     *            给定的IP，如218.240.38.69 
     * @param maskBit
     *            给定的掩码位，如30 
     * @return 起始IP的长整型表示
     */
    public static Long getBeginIpLong(String ip, String maskBit)
    {
        return getIpFromString(ip) & getIpFromString(getMaskByMaskBit(maskBit));
    }
    /**
     * 根据 ip/掩码位 计算IP段的终止IP 如 IP串 218.240.38.69/30 
     *
     * @param ip
     *            给定的IP，如218.240.38.69 
     * @param maskBit
     *            给定的掩码位，如30 
     * @return 终止IP的字符串表示
     */
    public static String getEndIpStr(String ip, String maskBit)
    {
        return getIpFromLong(getEndIpLong(ip, maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的终止IP 如 IP串 218.240.38.69/30 
     *
     * @param ip
     *            给定的IP，如218.240.38.69 
     * @param maskBit
     *            给定的掩码位，如30 
     * @return 终止IP的长整型表示
     */
    public static Long getEndIpLong(String ip, String maskBit)
    {
        return getBeginIpLong(ip, maskBit)
                + ~getIpFromString(getMaskByMaskBit(maskBit));
    }


    /**
     * 根据子网掩码转换为掩码位 如 255.255.255.252转换为掩码位 为 30 
     *
     * @param netmarks
     * @return
     */
    public static int getNetMask(String netmarks)
    {
        StringBuilder sbf;
        String str;
        int inetmask = 0;
        int count = 0;
        String[] ipList = netmarks.split("\\.");
        for (int n = 0; n < ipList.length; n++)
        {
            sbf = toBin(Integer.parseInt(ipList[n]));
            str = sbf.reverse().toString();
            count = 0;
            for (int i = 0; i < str.length(); i++)
            {
                i = str.indexOf('1', i);
                if (i == -1)
                {
                    break;
                }
                count++;
            }
            inetmask += count;
        }
        return inetmask;
    }

    /**
     * 计算子网大小 
     *
     *            掩码位 
     * @return
     */
    public static int getPoolMax(int maskBit)
    {
        if (maskBit <= 0 || maskBit >= 32)
        {
            return 0;
        }
        return (int) Math.pow(2, 32 - maskBit) - 2;
    }
    private static StringBuilder toBin(int x)
    {
        StringBuilder result = new StringBuilder();
        result.append(x % 2);
        x /= 2;
        while (x > 0)
        {
            result.append(x % 2);
            x /= 2;
        }
        return result;
    }

    public static String getMaskMap(String maskBit) {
        if ("1".equals(maskBit)) return "128.0.0.0";
        if ("2".equals(maskBit)) return "192.0.0.0";
        if ("3".equals(maskBit)) return "224.0.0.0";
        if ("4".equals(maskBit)) return "240.0.0.0";
        if ("5".equals(maskBit)) return "248.0.0.0";
        if ("6".equals(maskBit)) return "252.0.0.0";
        if ("7".equals(maskBit)) return "254.0.0.0";
        if ("8".equals(maskBit)) return "255.0.0.0";
        if ("9".equals(maskBit)) return "255.128.0.0";
        if ("10".equals(maskBit)) return "255.192.0.0";
        if ("11".equals(maskBit)) return "255.224.0.0";
        if ("12".equals(maskBit)) return "255.240.0.0";
        if ("13".equals(maskBit)) return "255.248.0.0";
        if ("14".equals(maskBit)) return "255.252.0.0";
        if ("15".equals(maskBit)) return "255.254.0.0";
        if ("16".equals(maskBit)) return "255.255.0.0";
        if ("17".equals(maskBit)) return "255.255.128.0";
        if ("18".equals(maskBit)) return "255.255.192.0";
        if ("19".equals(maskBit)) return "255.255.224.0";
        if ("20".equals(maskBit)) return "255.255.240.0";
        if ("21".equals(maskBit)) return "255.255.248.0";
        if ("22".equals(maskBit)) return "255.255.252.0";
        if ("23".equals(maskBit)) return "255.255.254.0";
        if ("24".equals(maskBit)) return "255.255.255.0";
        if ("25".equals(maskBit)) return "255.255.255.128";
        if ("26".equals(maskBit)) return "255.255.255.192";
        if ("27".equals(maskBit)) return "255.255.255.224";
        if ("28".equals(maskBit)) return "255.255.255.240";
        if ("29".equals(maskBit)) return "255.255.255.248";
        if ("30".equals(maskBit)) return "255.255.255.252";
        if ("31".equals(maskBit)) return "255.255.255.254";
        if ("32".equals(maskBit)) return "255.255.255.255";
        return "-1";
    }

    public static double ipToDouble(String ip) {
        String[] arr = ip.split("\\.");
        double d1 = Double.parseDouble(arr[0]);
        double d2 = Double.parseDouble(arr[1]);
        double d3 = Double.parseDouble(arr[2]);
        double d4 = Double.parseDouble(arr[3]);
        return d1 * Math.pow(256, 3) + d2 * Math.pow(256, 2) + d3 * 256 + d4;
    }
    public static List<String> parseIpMaskRange2(String cidr){
        String ip = cidr.split("/")[0];
        String mask = cidr.split("/")[1];
        List<String> list=new ArrayList<>();
        if ("32".equals(mask)) {
            list.add(ip);
        }else{
            String startIp=getBeginIpStr(ip, mask);
            String endIp=getEndIpStr(ip, mask);
            if (!"31".equals(mask)) {
                String subStart=startIp.split("\\.")[0]+"."+startIp.split("\\.")[1]+"."+startIp.split("\\.")[2]+".";
                String subEnd=endIp.split("\\.")[0]+"."+endIp.split("\\.")[1]+"."+endIp.split("\\.")[2]+".";
                startIp=subStart+(Integer.parseInt(startIp.split("\\.")[3])+1);
                endIp=subEnd+(Integer.parseInt(endIp.split("\\.")[3])-1);
            }
            list=parseIpRange(startIp, endIp);
        }
        return list;
    }

    // 将IP地址转换为整数
    private static int ipToInt(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        int ip = 0;
        for (int i = 0; i < 4; i++) {
            ip <<= 8;
            ip |= Integer.parseInt(parts[i]);
        }
        return ip;
    }

    // 将整数转换为IP地址
    private static String intToIp(int ip) {
        return ((ip >> 24) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                (ip & 0xFF);
    }

    // 解析IP范围并返回所有IP列表
    public static List<String> parseRange(String range) {
        List<String> ips = new ArrayList<>();
        String[] parts = range.split("-");
        String startIP = parts[0];
        String endIP = parts[1];

        // 将起始和结束IP地址转换为整数
        int start = ipToInt(startIP);
        int end = ipToInt(endIP);

        // 从起始IP循环到结束IP
        for (int i = start; i <= end; i++) {
            // 将当前整数转换为IP地址并加入列表
            ips.add(intToIp(i));
            System.err.println(intToIp(i));

        }

        return ips;
    }
    public static List<String> parseRange3(String range) {
        List<String> ipList = new ArrayList<>();

        // Split the input string into base IP and range end
        String[] parts = range.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input format. Expected format: 192.168.100.1-254");
        }

        String baseIP = parts[0].substring(0, parts[0].lastIndexOf('.') + 1);
        String startIP = parts[0];
        int endRange = Integer.parseInt(parts[1]);

        // Extract the starting IP's last segment
        int startRange = Integer.parseInt(startIP.substring(startIP.lastIndexOf('.') + 1));

        // Generate the list of IPs
        for (int i = startRange; i <= endRange; i++) {
            ipList.add(baseIP + i);
        }

        return ipList;
    }
    /**
     * 对给定的IP地址列表进行ping测试，返回可达的IpNode对象列表
     *
     * @param ips 需要ping测试的IP地址列表
     * @return 可达的IpNode对象列表
     */
    public  List<IpNode> pingIPs(List<String> ips) {
        int timeout = 1000; // ping超时时间（毫秒）
        int numberOfThreads = 50; // 使用的线程数
        List<IpNode> ipNodes = new ArrayList<>(); // 存储可达的IpNode对象
        // 创建固定线程池
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        // 遍历每个IP地址，提交ping任务到线程池
        for (String ip : ips) {
            executor.submit(() -> {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
                // 检查IP地址是否可达
                Boolean reachable = null;
                try {
                    reachable = inetAddress.isReachable(timeout);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (reachable) {
                        IpNode node = new IpNode();
                        node.setIp(ip);
                        node.setHostName(inetAddress.getHostName());
                        node.setPingState("Reachable");
                    String macAddress = "Unknown MAC";
                    try{
                        macAddress = getMacAddress(ip);
                    }catch (Exception e){
                    }
                    if (macAddress==null){
                        macAddress = "Unknown MAC";
                    }
                        node.setMacAddress(macAddress);
                        // 获取MAC地址前缀
                        String macPrefix = macAddress.replace("-", "").toUpperCase();
                        // 根据sqlite表查询mac_vendor表，根据mac获取vendor
                    String vendor = "Unknown vendor";
                    try{
                        vendor = configService.getVendorByMacPrefix(macPrefix);
                    }catch (Exception e){

                    }
                    if (vendor==null){
                        vendor = "Unknown vendor";
                    }
                    node.setVendor(vendor);
                    // 获取当前系统时间
                    LocalDateTime now = LocalDateTime.now();
                    // 定义日期时间格式
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    // 格式化当前时间
                    String formattedNow = now.format(formatter);
                    node.setDate(formattedNow);
                        // 设置其他属性（类型和厂商信息）
                        node.setType("Unknown Type");
                        // 同步添加可达IP地址到列表
                        synchronized (ipNodes) {
                            ipNodes.add(node);
                        }
                        System.out.println(ip + " is reachable.");
                    }
            });
        }
        // 关闭线程池
        executor.shutdown();
        try {
            // 等待所有任务完成，最长等待1小时
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 对可达的IP地址进行排序
        Collections.sort(ipNodes, (node1, node2) -> {
            String[] octets1 = node1.getIp().split("\\.");
            String[] octets2 = node2.getIp().split("\\.");
            for (int i = 0; i < 4; i++) {
                int part1 = Integer.parseInt(octets1[i]);
                int part2 = Integer.parseInt(octets2[i]);
                if (part1 != part2) {
                    return part1 - part2;
                }
            }
            return 0;
        });
        // 输出排序后的可达IP地址
        System.out.println("Discovered Hosts (sorted):");
        for (IpNode node : ipNodes) {
            System.out.println(node);
        }
        return ipNodes; // 返回排序后的可达IpNode对象列表
    }
    public  void  pingIPsLiu(List<String> ips, SseEmitter emitter) throws IOException {
        int timeout = 3000; // ping超时时间（毫秒）
        int numberOfThreads = 50; // 使用的线程数
        // 创建固定线程池
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        // 计数器，用于跟踪已完成的任务
        AtomicInteger completedTasks = new AtomicInteger(0);
        // 遍历每个IP地址，提交ping任务到线程池
        for (String ip : ips) {
            executorService.submit(() -> {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
                // 检查IP地址是否可达
                Boolean reachable = null;
                try {
                    reachable = inetAddress.isReachable(timeout);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (reachable != null&&reachable) {
                    IpNode node = new IpNode();
                    node.setIp(ip);
                    node.setHostName(inetAddress.getHostName());
                    node.setPingState("Reachable");
                    String macAddress = "Unknown MAC";
                    try{
                        macAddress = getMacAddress(ip);
                    }catch (Exception e){
                    }
                    if (macAddress==null){
                        macAddress = "Unknown MAC";
                    }
                    node.setMacAddress(macAddress);
                    // 获取MAC地址前缀
                    String macPrefix = macAddress.replace("-", "").toUpperCase();
                    // 根据sqlite表查询mac_vendor表，根据mac获取vendor
                    String vendor = "Unknown vendor";
                    try{
                        vendor = configService.getVendorByMacPrefix(macPrefix);
                    }catch (Exception e){

                    }
                    if (vendor==null){
                        vendor = "Unknown vendor";
                    }
                    node.setVendor(vendor);
                    // 获取当前系统时间
                    LocalDateTime now = LocalDateTime.now();
                    // 定义日期时间格式
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    // 格式化当前时间
                    String formattedNow = now.format(formatter);
                    node.setDate(formattedNow);
                    // 设置其他属性（类型和厂商信息）
                    node.setType("Unknown Type");
                    // 每次发现一个可达IP，就通过SSE发送给前端
                    try {
                        emitter.send(node);
                    } catch (IOException e) {
                        e.printStackTrace();
                        emitter.completeWithError(e);
                    }
                }
            });
        }
        executorService.shutdown();

        // 等待所有任务完成，但允许部分返回结果
        try {
            executorService.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            executorService.shutdownNow(); // 强制终止线程池
            Thread.currentThread().interrupt();
        }

        // 如果需要在所有任务完成后通知前端可以完成
        emitter.complete();
    }
    public  void  pingIPsLiu2(List<String> ips, SseEmitter emitter,Integer timeout) throws IOException {
//        int timeout = 3000; // ping超时时间（毫秒）
        int numberOfThreads = 50; // 使用的线程数
        // 创建固定线程池
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        // 计数器，用于跟踪已完成的任务
        AtomicInteger completedTasks = new AtomicInteger(0);
        // 遍历每个IP地址，提交ping任务到线程池
        for (String ip : ips) {
            executorService.submit(() -> {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
                // 检查IP地址是否可达
                Boolean reachable = null;
                try {
                    reachable = inetAddress.isReachable(timeout);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (reachable != null&&reachable) {
                    IpNode node = new IpNode();
                    node.setIp(ip);
                    node.setHostName(inetAddress.getHostName());
                    node.setPingState("Reachable");
                    String macAddress = "Unknown MAC";
                    try{
                        macAddress = getMacAddress(ip);
                    }catch (Exception e){
                    }
                    if (macAddress==null){
                        macAddress = "Unknown MAC";
                    }
                    node.setMacAddress(macAddress);
                    // 获取MAC地址前缀
                    String macPrefix = macAddress.replace("-", "").toUpperCase();
                    // 根据sqlite表查询mac_vendor表，根据mac获取vendor
                    String vendor = "Unknown vendor";
                    try{
                        vendor = configService.getVendorByMacPrefix(macPrefix);
                    }catch (Exception e){

                    }
                    if (vendor==null){
                        vendor = "Unknown vendor";
                    }
                    node.setVendor(vendor);
                    // 获取当前系统时间
                    LocalDateTime now = LocalDateTime.now();
                    // 定义日期时间格式
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    // 格式化当前时间
                    String formattedNow = now.format(formatter);
                    node.setDate(formattedNow);
                    // 设置其他属性（类型和厂商信息）
                    node.setType("Unknown Type");
                    // 每次发现一个可达IP，就通过SSE发送给前端
                    try {
                        emitter.send(node);
                    } catch (IOException e) {
                        e.printStackTrace();
                        emitter.completeWithError(e);
                    }
                }
            });
        }
        executorService.shutdown();

        // 等待所有任务完成，但允许部分返回结果
        try {
            if (timeout==300){
                executorService.awaitTermination(10, TimeUnit.SECONDS);
            }else {
                executorService.awaitTermination(1, TimeUnit.HOURS);
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow(); // 强制终止线程池
            Thread.currentThread().interrupt();
        }

        // 如果需要在所有任务完成后通知前端可以完成
        emitter.complete();
    }
    public List<IpNode> pingIPsNoExe(List<String> ips) {
        int timeout = 1000; // ping超时时间（毫秒）
        List<IpNode> ipNodes = new ArrayList<>(); // 存储可达的IpNode对象
        // 遍历每个IP地址，进行ping测试
        for (String ip : ips) {
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }

            // 检查IP地址是否可达
            Boolean reachable = null;
            try {
                reachable = inetAddress.isReachable(timeout);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (reachable) {
                IpNode node = new IpNode();
                node.setIp(ip);
                node.setHostName(inetAddress.getHostName());
                node.setPingState("Reachable");

                String macAddress = "Unknown MAC";
                try {
                    macAddress = getMacAddress(ip);
                } catch (Exception e) {
                }
                if (macAddress == null) {
                    macAddress = "Unknown MAC";
                }
                node.setMacAddress(macAddress);

                // 获取MAC地址前缀
                String macPrefix = macAddress.replace("-", "").toUpperCase();
                // 根据sqlite表查询mac_vendor表，根据mac获取vendor
                String vendor = "Unknown vendor";
                try {
                    vendor = configService.getVendorByMacPrefix(macPrefix);
                } catch (Exception e) {
                }
                if (vendor == null) {
                    vendor = "Unknown vendor";
                }
                node.setVendor(vendor);

                // 获取当前系统时间
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedNow = now.format(formatter);
                node.setDate(formattedNow);

                // 设置其他属性（类型和厂商信息）
                node.setType("Unknown Type");

                ipNodes.add(node); // 添加可达IP地址到列表
                System.out.println(ip + " is reachable.");
            }
        }

        return ipNodes; // 返回排序后的可达IpNode对象列表
    }


    public static String getMacAddress(String ip) {
        String os = System.getProperty("os.name").toLowerCase();
        String command = "";

        // 根据操作系统选择命令
        if (os.contains("win")) {
            command = "arp -a " + ip;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            command = "arp " + ip;
        } else {
            System.err.println("Unsupported operating system.");
            return null;
        }

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(ip)) {
                    String[] segments = line.split("\\s+");
                    for (String segment : segments) {
                        if (segment.matches("([\\dA-Fa-f]{2}[:-]){5}[\\dA-Fa-f]{2}")) {
                            return segment;
                        }
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}  