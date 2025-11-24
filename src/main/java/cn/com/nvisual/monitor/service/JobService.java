package cn.com.nvisual.monitor.service;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JobService {
    private JSONArray job;

    @Autowired
    private TokenService tokenService;

    @Autowired
    AuditHistoryService auditHistoryService;



    @PostConstruct
    public void init() {
        this.job = fetchJobFromNVisual(); // 启动时调用nVisual接口获取job列表
    }

    private JSONArray fetchJobFromNVisual() {
        String token = tokenService.getToken();
        String paramValue = "/diagramApi/wapi/v1/monitor/job";
        System.err.println("从nvisual获取job列表");
         JSONArray jsonArray = auditHistoryService.getJsonArray(paramValue, token);
        System.err.println("获取job列表成功："+jsonArray.toString());
        return jsonArray;
    }

    public JSONArray getJob() {
        return job;
    }
    public JSONArray addJob(String s) {
        job.add(s);
        return job;
    }public JSONArray editJob(JSONArray s) {
        job = s;
        return job;
    }

}

