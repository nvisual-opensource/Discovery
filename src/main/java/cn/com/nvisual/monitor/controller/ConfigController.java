package cn.com.nvisual.monitor.controller;
import cn.com.nvisual.monitor.config.AppConfig;
import cn.com.nvisual.monitor.service.JobService;
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
    @Autowired
    JobService jobService;
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
        if (newConfig.containsKey("prometheus")) {
            appConfig.setPrometheus(newConfig.get("prometheus"));
            configService.updateSetting("prometheus", newConfig.get("prometheus"));
        }
        if (newConfig.containsKey("grafana")) {
            appConfig.setGrafana(newConfig.get("grafana"));
            configService.updateSetting("grafana", newConfig.get("grafana"));
        }
        if (newConfig.containsKey("syncPeriod")) {
            appConfig.setSyncPeriod(Integer.parseInt(newConfig.get("syncPeriod")));
            configService.updateSetting("syncPeriod", newConfig.get("syncPeriod"));
        }
        if (newConfig.containsKey("username")) {
            appConfig.setUsername(newConfig.get("username"));
            configService.updateSetting("username", newConfig.get("username"));
        }
        if (newConfig.containsKey("password")) {
            appConfig.setPassword(newConfig.get("password"));
            configService.updateSetting("password", newConfig.get("password"));
        }
        if (newConfig.containsKey("alertmanager")) {
            appConfig.setAlertmanager(newConfig.get("alertmanager"));
            configService.updateSetting("alertmanager", newConfig.get("alertmanager"));
        }


        // 准备返回内容
        Map<String, String> updatedConfig = new HashMap<>(newConfig);

        tokenService.init();
        jobService.init();
        return ResponseEntity.ok(updatedConfig);
    }
    @GetMapping("/get_config")
    public ResponseEntity<Map<String, String>> getConfig() throws IOException {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("nVisual", appConfig.getnVisual());
        configMap.put("prometheus", appConfig.getPrometheus());
        configMap.put("grafana", appConfig.getGrafana());
        configMap.put("syncPeriod", String.valueOf(appConfig.getSyncPeriod()));
        configMap.put("username", appConfig.getUsername());
        configMap.put("password", appConfig.getPassword());
        configMap.put("alertmanager", appConfig.getAlertmanager());

        return ResponseEntity.ok(configMap);
    }
}
