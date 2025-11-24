package cn.com.nvisual.monitor.service;
import cn.com.nvisual.monitor.config.AppConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.com.nvisual.monitor.model.IpNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IpDiscoverService {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    IpUtil ipUtil;
    @Autowired
    AuditHistoryService auditHistoryService;
    @Autowired
    ConfigService configService;

    @Autowired
    PDiscoveryService pDiscoveryService;
    private List<IpNode> nm;
    int waitTimeInMillis = 10000; // 10秒


    @PostConstruct
    public void init() {
        new Thread(this::networkMonitoring).start(); // 启动时调用nVisual接口开始入网监测
    }
    private void networkMonitoring() {//入网监测
        for (int i = 1; i >0 ; i++) {
            System.err.println("i:"+i);
            //获取缓存内的nVisual设备列表
             JSONArray prometheusdiscovery = pDiscoveryService.getPrometheusDiscovery();
            System.err.println("缓存内的nVisual设备列表大小:"+prometheusdiscovery.size());
                     //取出nVisual设备列表内ip
                     List<String> nVisualIps = new ArrayList<>();
                     for (int j = 0; j < prometheusdiscovery.size(); j++) {
                         JSONObject obj = prometheusdiscovery.getJSONObject(j);
                         String oip = obj.getString("ip");
                         nVisualIps.add(oip);
                     }
                     System.err.println("nVisualIps:"+nVisualIps);
                     List<String> disIps = new ArrayList<>();
                     if (nm!=null){
                         List<IpNode> filteredHostList = nm.stream()
                                 .filter(host -> !nVisualIps.contains(host.getIp()))
                                 .collect(Collectors.toList());
                         nm = filteredHostList;
                         System.err.println("nm0："+nm);

                         for (IpNode in:
                                 nm) {
                             String ip = in.getIp();
                             nVisualIps.add(ip);
                         }
                     }

                    List<String> allCidrIps = configService.getAllCidrIps();
                     for (String cidr:
                             allCidrIps) {
                         System.err.println("cidr："+cidr);
                         List<String> ips = new ArrayList<>();
                         if (cidr.contains("-")){
                             final String s = cidr.split("-")[1];
                             if (s.contains(".")){
                                 ips = IpUtil.parseRange(cidr);
                             }else {
                                 ips = IpUtil.parseRange3(cidr);
                             }
                         }else if (cidr.contains("/")){
                             ips = IpUtil.parseIpMaskRange2(cidr);
                         }else {
                             ips.add(cidr);
                         }
                         // 过滤掉nVisualIps中的IP
                         for (String ip : ips) {
                             if (!nVisualIps.contains(ip)) {
                                 disIps.add(ip); // 将不在nVisualIps中的IP添加到disIps中
                             }
                         }
                     }
                    System.err.println("disIps:"+disIps);
                     List<IpNode> reachableIPs2 = ipUtil.pingIPs(disIps);
                     if (nm==null){
                         List<IpNode> nm1 = new ArrayList<>();
                         nm = nm1;
                     }
                     if (reachableIPs2!=null){
                         System.err.println("reachableIPs2："+reachableIPs2);
                         List<IpNode> nm1 = new ArrayList<>();
                         nm1.addAll(reachableIPs2);
                         nm1.addAll(nm);
                         nm = nm1;
                         System.err.println("nm："+nm);

                     }

            // 添加等待时间
            try {
                Thread.sleep(waitTimeInMillis);
            } catch (InterruptedException e) {
                // 处理被中断的情况
                System.err.println("Thread was interrupted: " + e.getMessage());
                // 如果需要，可以在这里处理中断逻辑
                break;
            }

        }
    }
    public List<IpNode> getNetworkMonitoring() {
        return nm;
    }

}

