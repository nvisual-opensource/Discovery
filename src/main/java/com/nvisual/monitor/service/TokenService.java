package com.nvisual.monitor.service;
import com.nvisual.monitor.ErrorResponses;
import com.nvisual.monitor.config.AppConfig;
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
        this.token = fetchTokenFromNVisual(); // 启动时调用nVisual接口获取Token
    }

    private String fetchTokenFromNVisual() {
        String nVisualUrl = appConfig.getnVisual();//nVisual=https://cloud.nvisual.com/diagramApi
        // 调用nVisual的接口获取Token，假设返回"dummyToken"
        //获取token
        String token = "";
        try{
            String url = nVisualUrl+"/diagramApi/wapi/v1/authenticate";
            Map getTokenMap = new HashMap<>();
            getTokenMap.put("username",appConfig.getUsername());
            getTokenMap.put("password",appConfig.getPassword());
            Map postReturnData = auditHistoryService.getTokenReturn(url, getTokenMap);
            if (postReturnData!=null) {
                System.out.println("请求token成功");
                token = "Bearer " + (String) postReturnData.get("access_token");
            }
            else {
                System.err.println("请求失败");
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
        if(token.equals("")) {
            System.err.println("请求失败");
            return null;
        }
//        System.err.println("btoken： "+token);
        return token;
    }

    public String getToken() {
        return token;
    }
    public String editToken(String t) {
        token = t;
        return token;
    }
    public String getNewToken() {
        String nVisualUrl = appConfig.getnVisual();//nVisual=https://cloud.nvisual.com/diagramApi
        // 调用nVisual的接口获取Token，假设返回"dummyToken"
        //获取token
        String token = "";
        try{
            String url = nVisualUrl+"/diagramApi/wapi/v1/authenticate";
            Map getTokenMap = new HashMap<>();
            getTokenMap.put("username",appConfig.getUsername());
            getTokenMap.put("password",appConfig.getPassword());
            Map postReturnData = auditHistoryService.getTokenReturn(url, getTokenMap);
            if (postReturnData!=null) {
                System.out.println("请求token成功");
                token = "Bearer " + (String) postReturnData.get("access_token");
            }
            else {
                System.err.println("请求失败");
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
        if(token.equals("")) {
            System.err.println("请求失败");
            return null;
        }

        editToken(token);
        return token;
    }

}

