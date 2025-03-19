package com.nvisual.monitor.service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nvisual.monitor.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService {

    private ScheduledFuture<?> scheduledFuture5;
    private ScheduledFuture<?> scheduledFuture7;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

//    private JSONArray prometheusDiscovery;
    @Autowired
    PDiscoveryService pDiscoveryService;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    AuditHistoryService auditHistoryService;
    @Autowired
    JobService jobService;
    @Autowired
    TokenService tokenService;

    @PostConstruct
    public void init() {
        try {
            scheduleTask5(); // 启动时设置定时器 从nvisual获取要检测的设备
            scheduleTask7(); // 启动时设置定时器 验证token是否可用
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // 记录日志
        }
    }


    public  void main(String[] args) {

    }

//    public void scheduleTask2() {//60s一次获取nvisual内有ip有monitor type的对象  ？合并3
//        int anotherSyncPeriod = 60;
//        if (scheduledFuture2 != null) {
//            scheduledFuture2.cancel(false);
//        }
//        scheduledFuture2 = scheduler.scheduleAtFixedRate(
//                this::prometheusdiscovery, 0, anotherSyncPeriod, TimeUnit.SECONDS
//        );
//    }

    public void scheduleTask5() {//60s一次从nvisual获取要检测的设备
        int initialDelay = 0; // 初始延迟

        int anotherSyncPeriod = 60;
        if (scheduledFuture5 != null) {
            scheduledFuture5.cancel(false);
        }
        scheduledFuture5 = scheduler.scheduleAtFixedRate(
                this::discoverDevices, initialDelay, anotherSyncPeriod, TimeUnit.SECONDS
        );
    }
    public void scheduleTask7() {//60s一次验证token
        int initialDelay = 0; // 初始延迟

        int anotherSyncPeriod = 60;
        if (scheduledFuture7 != null) {
            scheduledFuture7.cancel(false);
        }
        scheduledFuture7 = scheduler.scheduleAtFixedRate(
                this::verifyToken, initialDelay, anotherSyncPeriod, TimeUnit.SECONDS
        );
    }

    public void discoverDevices() {
        JSONArray prometheusDiscovery = pDiscoveryService.fetchPrometheusDiscoveryFromNVisual();
        pDiscoveryService.editPrometheusDiscovery(prometheusDiscovery);
        System.err.println("nvisual内要全量同步的设备列表内容大小:"+prometheusDiscovery.size());
    }

    public void verifyToken() {//全量同步端口
        String token = tokenService.getToken();
        if (token==null){
            token = tokenService.getNewToken();
        }else {
            if (token==""){
                token = tokenService.getNewToken();
            }else {
                String nvisualParamValue = "/diagramApi/wapi/v1/global_settings/front_end";
                System.err.println("\n验证token是否可用url:"+nvisualParamValue);
                JSONArray jsonArray = auditHistoryService.getJsonArray(nvisualParamValue, token);
                if (jsonArray==null){
                    token = tokenService.getNewToken();
                }else {
                    if (jsonArray.size()==0){
                        token = tokenService.getNewToken();
                    }
                }
            }
        }
        System.err.println("token:"+token);

    }


}
