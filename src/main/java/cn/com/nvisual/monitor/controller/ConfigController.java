package cn.com.nvisual.monitor.controller;
import cn.com.nvisual.monitor.config.AppConfig;
//import cn.com.nvisual.monitor.service.JobService;
import cn.com.nvisual.monitor.service.ConfigService;
import cn.com.nvisual.monitor.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wapi/v1")
public class ConfigController {

    @Autowired
    private AppConfig appConfig;
//    @Autowired
//    JobService jobService;
    @Autowired
    TokenService tokenService;

    @Autowired
    private ConfigService configService;

    @PostMapping("/update_config")
    public ResponseEntity<Map<String, String>> updateConfig(@RequestBody Map<String, String> newConfig) throws IOException {

        if (newConfig.containsKey("nVisual")) {
            appConfig.setnVisual(newConfig.get("nVisual"));
            configService.updateSetting("nVisual", newConfig.get("nVisual"));
        }

        if (newConfig.containsKey("syncPeriod")) {
            appConfig.setSyncPeriod(Integer.parseInt(newConfig.get("syncPeriod")));
            configService.updateSetting("syncPeriod", newConfig.get("syncPeriod"));
        }
        if (newConfig.containsKey("nVisual_api_key")) {
            appConfig.setnVisual_api_key(newConfig.get("nVisual_api_key"));
            configService.updateSetting("nVisual_api_key", newConfig.get("nVisual_api_key"));
        }
        if (newConfig.containsKey("AI")) {
            appConfig.setAI(newConfig.get("AI"));
            configService.updateSetting("AI", newConfig.get("AI"));
        }
        if (newConfig.containsKey("AI_api_key")) {
            appConfig.setAI_api_key(newConfig.get("AI_api_key"));
            configService.updateSetting("AI_api_key", newConfig.get("AI_api_key"));
        }


        // 准备返回内容
        Map<String, String> updatedConfig = new HashMap<>(newConfig);
        tokenService.init();
        return ResponseEntity.ok(updatedConfig);
    }
    @GetMapping("/get_config")
    public ResponseEntity<Map<String, String>> getConfig() throws IOException {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("nVisual", appConfig.getnVisual());
        configMap.put("nVisual_api_key", appConfig.getnVisual_api_key());
        configMap.put("AI", appConfig.getAI());
        configMap.put("AI_api_key", appConfig.getAI_api_key());
        configMap.put("syncPeriod", String.valueOf(appConfig.getSyncPeriod()));
        return ResponseEntity.ok(configMap);
    }
}
