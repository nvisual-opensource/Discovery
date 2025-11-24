package cn.com.nvisual.monitor.service;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PDiscoveryService {
    private JSONArray prometheusDiscovery;

    @Autowired
    private TokenService tokenService;

    @Autowired
    AuditHistoryService auditHistoryService;


    @PostConstruct
    public void init() {
        this.prometheusDiscovery = fetchPrometheusDiscoveryFromNVisual(); // 启动时调用nVisual接口获取job列表
    }

     JSONArray fetchPrometheusDiscoveryFromNVisual() {
        String token = tokenService.getToken();
        String nvisualParamValue = "/diagramApi/wapi/v1/monitor/prometheus_monitor/all";
        System.err.println("\n取nvisual内要全量同步的设备列表url:"+nvisualParamValue);
        JSONArray jsonArray = auditHistoryService.getJsonArray(nvisualParamValue, token);
        return jsonArray;
    }

    public JSONArray getPrometheusDiscovery() {
        return prometheusDiscovery;
    }
    public JSONArray addPrometheusDiscovery(String s) {
        prometheusDiscovery.add(s);
        return prometheusDiscovery;
    }public JSONArray editPrometheusDiscovery(JSONArray s) {
        prometheusDiscovery = s;
        return prometheusDiscovery;
    }

}

