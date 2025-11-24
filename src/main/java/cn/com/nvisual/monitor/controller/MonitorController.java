package cn.com.nvisual.monitor.controller;

import cn.com.nvisual.monitor.model.Health;
import cn.com.nvisual.monitor.config.AppConfig;
import cn.com.nvisual.monitor.service.AuditHistoryService;
import cn.com.nvisual.monitor.service.JobService;
import cn.com.nvisual.monitor.service.PDiscoveryService;
import cn.com.nvisual.monitor.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
            System.err.println("url:"+url);
            System.err.println("username:"+health.getUsername());
            System.err.println("password:"+health.getPassword());
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
