package cn.com.nvisual.monitor.service;
import cn.com.nvisual.monitor.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    private String token;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    AuditHistoryService auditHistoryService;


    @PostConstruct
    public void init() {
        this.token = appConfig.getnVisual_api_key(); // 启动时调用nVisual接口获取Token
    }


    public String getToken() {
        return token;
    }
    public String getNewToken() {
        String token = appConfig.getnVisual_api_key();
        return token;
    }

}

