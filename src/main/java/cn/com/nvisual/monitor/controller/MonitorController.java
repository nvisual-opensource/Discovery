package cn.com.nvisual.monitor.controller;

import cn.com.nvisual.monitor.model.Health;
import cn.com.nvisual.monitor.config.AppConfig;
import cn.com.nvisual.monitor.service.AuditHistoryService;
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
//    @Autowired
//    JobService jobService;
    @Autowired
    TokenService tokenService;


    @Autowired
    AuditHistoryService auditHistoryService;
    @Autowired
    PDiscoveryService pDiscoveryService;



   }
