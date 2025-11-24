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
    private String prometheus;
    private String grafana;
    private String username;
    private String password;
    private String alertmanager;
    private int syncPeriod;

    @PostConstruct
    public void init() {
        nVisual = configService.getSetting("nVisual");
        prometheus = configService.getSetting("prometheus");
        grafana = configService.getSetting("grafana");
        username = configService.getSetting("username");
        password = configService.getSetting("password");
        alertmanager = configService.getSetting("alertmanager");
        syncPeriod = Integer.parseInt(configService.getSetting("syncPeriod"));

        logConfig();
    }

    public void logConfig() {
        logger.info("nVisual: {}", nVisual);
        logger.info("Prometheus: {}", prometheus);
        logger.info("Grafana: {}", grafana);
        logger.info("Username: {}", username);
        logger.info("Password: {}", password);
        logger.info("SyncPeriod: {}", syncPeriod);
        logger.info("alertmanager: {}", alertmanager);
    }

    // Getters and Setters

    public String getAlertmanager() {
        return alertmanager;
    }

    public void setAlertmanager(String alertmanager) {
        this.alertmanager = alertmanager;
    }

    public String getnVisual() {
        return nVisual;
    }

    public void setnVisual(String nVisual) {
        this.nVisual = nVisual;
    }

    public String getPrometheus() {
        return prometheus;
    }

    public void setPrometheus(String prometheus) {
        this.prometheus = prometheus;
    }

    public String getGrafana() {
        return grafana;
    }

    public void setGrafana(String grafana) {
        this.grafana = grafana;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSyncPeriod() {
        return syncPeriod;
    }

    public void setSyncPeriod(int syncPeriod) {
        this.syncPeriod = syncPeriod;
    }
}
