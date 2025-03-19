package com.nvisual.monitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nvisual.monitor.config.AppConfig;
import com.nvisual.monitor.model.Health;
import com.nvisual.monitor.service.AuditHistoryService;
import com.nvisual.monitor.service.JobService;
import com.nvisual.monitor.service.PDiscoveryService;
import com.nvisual.monitor.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wapi/v1")
public class MonitorController {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    JobService jobService;
    @Autowired
    TokenService tokenService;


    @Autowired
    AuditHistoryService auditHistoryService;
    @Autowired
    PDiscoveryService pDiscoveryService;


    @RequestMapping(value = "/monitor/health", method = RequestMethod.POST)
    public Map health(@RequestBody Health health
            , HttpServletResponse httpServletResponse) {
        String url = health.getUrl();
        String type = health.getType();
        if (type.equals("nVisual")) {
            Map m = new HashMap();
             url = url+"/diagramApi/wapi/v1/authenticate";
            Map getTokenMap = new HashMap<>();
            getTokenMap.put("username",health.getUsername());
            getTokenMap.put("password",health.getPassword());
            Map postReturnData = auditHistoryService.getTokenReturn(url, getTokenMap);
            if (postReturnData!=null) {
                System.out.println("请求token成功");
                String t = "Bearer " + (String) postReturnData.get("access_token");
                tokenService.editToken(t);
                jobService.init();
                m.put("code", 200);
                m.put("message", "verification successful");
                return m;
            }else{
                m.put("code", 500);
                m.put("message", "verification failed");
                return m;
            }
        }else {
            Map m = new HashMap();
            m.put("code", 500);
            m.put("message", "verification failed");
            return null;
        }
    }

   }
