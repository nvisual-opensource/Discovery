package com.nvisual.monitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nvisual.monitor.config.AppConfig;
import com.nvisual.monitor.model.*;
import com.nvisual.monitor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;


@RestController
@RequestMapping("/wapi/v1")
public class PingScanner {

    @Autowired
    TokenService tokenService;
    @Autowired
    IpUtil ipUtil;
    @Autowired
    AuditHistoryService auditHistoryService;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    IpDiscoverService ipDiscoverService;
    @Autowired
    ConfigService configService;
    @Autowired
    JobService jobService;

    //ip扫描发现列表
    @RequestMapping(value = "/monitor/ip_discovery_old", method = RequestMethod.POST)
    public List<IpNode> getIpDiscoveryOld(@RequestBody String ipRange
            , HttpServletResponse httpServletResponse) {
        List<IpNode> resultArray = new ArrayList<>();

        if (ipRange.contains("-")){
            final String s = ipRange.split("-")[1];
            if (s.contains(".")){
                List<String> ips = IpUtil.parseRange(ipRange);
                List<IpNode> reachableIPs2 = ipUtil.pingIPs(ips);
                resultArray =  reachableIPs2;
            }else {
                List<String> ips = IpUtil.parseRange3(ipRange);
                List<IpNode> reachableIPs2 = ipUtil.pingIPs(ips);
                resultArray =  reachableIPs2;
            }

        }else if (ipRange.contains("/")){
            List<String> ips = IpUtil.parseIpMaskRange2(ipRange);
            List<IpNode> reachableIPs2 = ipUtil.pingIPs(ips);
            resultArray =  reachableIPs2;
        }else {
            List<String> ips = new ArrayList<>();
            ips.add(ipRange);
            try{
                List<IpNode> reachableIPs2 = ipUtil.pingIPs(ips);
                resultArray =  reachableIPs2;
            }catch (Exception e){

            }
        }
        return resultArray;
    }
    //ip扫描发现列表
    @RequestMapping(value = "/monitor/ip_discovery", method = RequestMethod.GET)
    public SseEmitter getIpDiscovery(@RequestParam String ipRange
            , HttpServletResponse httpServletResponse) {
        SseEmitter emitter = new SseEmitter();
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        List<String> ips = new ArrayList<>();
        Integer timeout = 3000;
        if (ipRange.contains("-")){
            final String s = ipRange.split("-")[1];
            if (s.contains(".")){
                 ips = IpUtil.parseRange(ipRange);
            }else {
                ips = IpUtil.parseRange3(ipRange);
            }

        }else if (ipRange.contains("/")){
             ips = IpUtil.parseIpMaskRange2(ipRange);
        }else {
            timeout = 300;
            ips = new ArrayList<>();
            ips.add(ipRange);
        }
        if (ips.size()<10){
            timeout = 300;
        }
        // 异步执行ping任务并流式返回每个可达IP
        List<String> finalIps = ips;
        Integer finalTimeout = timeout;
        executorService.submit(() -> {
            try {
                ipUtil.pingIPsLiu2(finalIps, emitter, finalTimeout);
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });

        // 设置超时处理
        emitter.onTimeout(() -> {
            emitter.complete();
        });

        return emitter;
    }
    @RequestMapping(value = "/monitor/ip_discoverys", method = RequestMethod.POST)
    public  List<IpNode> pingIPs(@RequestBody List<String> ips) {
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
                        macAddress = IpUtil.getMacAddress(ip);
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

    //ip扫描发现纳管
    @RequestMapping(value = "/monitor/ip_creact", method = RequestMethod.POST)
    public Map creactNodeByIp(@RequestBody CreatIpReq creatIpReq
            , HttpServletResponse httpServletResponse) {
        Map ret = new HashMap();
        List<IpAndName> ipAndName = creatIpReq.getIpAndName();
        Long diagramId = creatIpReq.getDiagramId();
        String typeName = creatIpReq.getTypeName();
        String nvisualDeviceUrl = appConfig.getnVisual()+"/diagramApi/wapi/v1/discovery/creatNode";

        String mt = "blackbox_icmp";
        if (creatIpReq.getJob()!=null){
            mt = creatIpReq.getJob();
        }
        String token = tokenService.getToken();

        for (int i = 0; i < ipAndName.size(); i++) {
            IpAndName in = ipAndName.get(i);
            Double x = 25d+(i%20)*50;
            Double y = 25d+(i/20)*50;

            Map ipCreatNode = new HashMap();
            ipCreatNode.put("name",in.getHostName());
            ipCreatNode.put("type",typeName);
            ipCreatNode.put("monitorType",mt);
            ipCreatNode.put("ip",in.getIp());
            ipCreatNode.put("diagramId",diagramId);
            if (creatIpReq.getDirection()!=null&&creatIpReq.getUnit()!=null){
                ipCreatNode.put("unit",creatIpReq.getUnit());
                ipCreatNode.put("direction",creatIpReq.getDirection());
            }
//            ipCreatNode.put("width",50d);
//            ipCreatNode.put("height",50d);
            ipCreatNode.put("x",x);
            ipCreatNode.put("y",y);

            auditHistoryService. postJSONToken2(nvisualDeviceUrl,token,ipCreatNode);
        }
        ret.put("code",200);
        ret.put("message","success");
        return ret;
    }
    @RequestMapping(value = "/job/get", method = RequestMethod.GET)
    public JSONArray getJob(HttpServletResponse httpServletResponse) {
        JSONArray job = jobService.getJob();
        return job;
    }
    //从nvisual获取目录
    @RequestMapping(value = "/monitor/directory/{nodeId}", method = RequestMethod.GET)
    public JSONArray getIpDiscovery(@PathVariable Long nodeId
            , HttpServletResponse httpServletResponse) {
        String token = tokenService.getToken();
        String paramValue = "/diagramApi/wapi/v1/paths/drill_down/"+nodeId+"/node_id";
        System.err.println("从nvisual获取目录列表");
        JSONArray jsonArray = auditHistoryService.getJsonArray(paramValue, token);
        System.err.println("获取目录列表成功："+jsonArray.toString());
        return jsonArray;
    }
    //从nvisual根据分组获取型号名列表
    @RequestMapping(value = "/monitor/models/{group}", method = RequestMethod.GET)
    public JSONArray getNodeTypeByGroup(@PathVariable Integer group
            , HttpServletResponse httpServletResponse) {
        String token = tokenService.getToken();
        String paramValue = "/diagramApi/wapi/v1/node_types/get_name_by_group/"+group;
        System.err.println("从nvisual获取型号名列表");
        JSONArray jsonArray = auditHistoryService.getJsonArray(paramValue, token);
        System.err.println("获取型号名列表成功："+jsonArray.toString());
        return jsonArray;
    }
    //入网监测,获取网段设置列表
    @RequestMapping(value = "/monitor/network/get_cidr", method = RequestMethod.GET)
    public List<NetworkSegment> getnetworkcidr( HttpServletResponse httpServletResponse) {
         List<NetworkSegment> allNetworkSegments = configService.getAllNetworkSegments();
        return allNetworkSegments;
    }
    //入网监测,新增网段设置
    @RequestMapping(value = "/monitor/network/add_cidr", method = RequestMethod.POST)
    public List<NetworkSegment> addnetworkcidr(@RequestBody NetworkSegment networkSegment,
                                               HttpServletResponse httpServletResponse) {
         NetworkSegment networkSegmentByCidr = configService.getNetworkSegmentByCidr(networkSegment.getCidr());
        if (networkSegmentByCidr==null){
            configService.addNetworkSegment(networkSegment.getCidr(),networkSegment.getDescription());
        }
         List<NetworkSegment> allNetworkSegments = configService.getAllNetworkSegments();
        return allNetworkSegments;
    }
    //入网监测,删除网段设置
    @RequestMapping(value = "/monitor/network/delete_cidr", method = RequestMethod.POST)
    public List<NetworkSegment> deletenetworkcidr(@RequestBody NetworkSegment networkSegment,
                                               HttpServletResponse httpServletResponse) {
        NetworkSegment networkSegmentByCidr = configService.getNetworkSegmentByCidr(networkSegment.getCidr());
        if (networkSegmentByCidr!=null){
            configService.deleteNetworkSegment(networkSegment.getCidr());
        }
        List<NetworkSegment> allNetworkSegments = configService.getAllNetworkSegments();
        return allNetworkSegments;
    }
    //入网监测,获取监测列表
    @RequestMapping(value = "/monitor/network/ip_discovery", method = RequestMethod.GET)
    public List<IpNode> getnetworkip(HttpServletResponse httpServletResponse) {
         List<IpNode> networkMonitoring = ipDiscoverService.getNetworkMonitoring();
        return networkMonitoring;
    }
    //从nvisual获取机房目录
    @RequestMapping(value = "/monitor/network/rack_room", method = RequestMethod.GET)
    public JSONObject getrackroom( HttpServletResponse httpServletResponse) {
        String token = tokenService.getToken();
        String paramValue = "/diagramApi/wapi/v1/getRackRooms";
        System.err.println("从nvisual获取机房目录列表");
        JSONObject jsonArray = auditHistoryService.getJsonObject(paramValue, token);
        System.err.println("获取机房目录列表成功："+jsonArray.toString());
        return jsonArray;
    }
    //从nvisual根据机房id获取机柜目录
    @RequestMapping(value = "/monitor/network/rack/{roomId}", method = RequestMethod.GET)
    public JSONObject getrackbyroom(@PathVariable Long roomId, HttpServletResponse httpServletResponse) {
        String token = tokenService.getToken();
        String paramValue = "/diagramApi/wapi/v1/getRacksByRoom/"+roomId;
        System.err.println("从nvisual根据机房获取机柜目录列表");
        JSONObject jsonArray = auditHistoryService.getJsonObject(paramValue, token);
        System.err.println("获取机柜目录列表成功："+jsonArray.toString());
        return jsonArray;
    }
    //从nvisual根据机柜id朝向和型号获取可用U位
    @RequestMapping(value = "/monitor/network/can_use_unit", method = RequestMethod.POST)
    public JSONObject getCanUseUnit(@RequestBody CanUseUnit canUseUnit, HttpServletResponse httpServletResponse) {
        String token = tokenService.getToken();
        String paramValue = "/diagramApi/wapi/v1/getCanUseUnit/rackId/"+canUseUnit.getRackId()+"/direction/"+canUseUnit.getDirection()+"/typeName/"+canUseUnit.getTypeName();
        System.err.println("获取可用U位");
        JSONObject jsonArray = auditHistoryService.getJsonObject(paramValue, token);
        System.err.println("可用U位："+jsonArray.toString());
        return jsonArray;
    }

}