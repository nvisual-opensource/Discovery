package cn.com.nvisual.monitor.config;

import cn.com.nvisual.monitor.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private ConfigService configService;

    private String nVisual;
    private String nVisual_api_key;
    private String AI;
    private String AI_api_key;
    private int syncPeriod;

    @PostConstruct
    public void init() {
        nVisual = configService.getSetting("nVisual");
        nVisual_api_key = configService.getSetting("nVisual_api_key");
        AI = configService.getSetting("AI");
        AI_api_key = configService.getSetting("AI_api_key");
        syncPeriod = Integer.parseInt(configService.getSetting("syncPeriod"));

        logConfig();
    }

    public void logConfig() {
        logger.info("nVisual: {}", nVisual);
        logger.info("nVisual_api_key: {}", nVisual_api_key);
        logger.info("AI: {}", AI);
        logger.info("AI_api_key: {}", AI_api_key);
        logger.info("syncPeriod: {}", syncPeriod);
    }

    // Getters and Setters



    public String getnVisual() {
        return nVisual;
    }

    public void setnVisual(String nVisual) {
        this.nVisual = nVisual;
    }

    public ConfigService getConfigService() {
        return configService;
    }

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    public String getnVisual_api_key() {
        return nVisual_api_key;
    }

    public void setnVisual_api_key(String nVisual_api_key) {
        this.nVisual_api_key = nVisual_api_key;
    }

    public String getAI() {
        return AI;
    }

    public void setAI(String AI) {
        this.AI = AI;
    }

    public String getAI_api_key() {
        return AI_api_key;
    }

    public void setAI_api_key(String AI_api_key) {
        this.AI_api_key = AI_api_key;
    }

    public int getSyncPeriod() {
        return syncPeriod;
    }

    public void setSyncPeriod(int syncPeriod) {
        this.syncPeriod = syncPeriod;
    }
}
