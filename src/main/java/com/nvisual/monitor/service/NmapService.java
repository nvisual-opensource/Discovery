package com.nvisual.monitor.service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nvisual.monitor.config.AppConfig;
import com.nvisual.monitor.controller.NmapController;
import com.nvisual.monitor.model.IpNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class NmapService {
    @Autowired
    ConfigService configService;

    // 调用Nmap并返回结果
    // 获取操作系统类型
    private String getOS() {
        return System.getProperty("os.name").toLowerCase();
    }
    private static final Logger logger = LoggerFactory.getLogger(NmapController.class);

    // 调用Nmap并实时返回输出
    public void executeNmapCommand(String instruction, Consumer<String> lineConsumer) throws Exception {
        String nmapPath;
        String os = getOS();

        // 获取Nmap路径并检查
            nmapPath = configService.getSetting("nmap");
        System.err.println("数据库中的nmap路径:"+nmapPath);


        if (nmapPath == null || nmapPath.isEmpty()) {
            logger.error("找不到Nmap路径，操作系统: {}", os);
            throw new Exception("找不到Nmap路径，操作系统: " + os); // 抛出特定异常
        }

        if (instruction.startsWith("nmap ")) {
            instruction = instruction.substring(5);
        }

        List<String> command = Arrays.asList(instruction.split(" "));
        ProcessBuilder processBuilder = os.contains("linux")
                ? new ProcessBuilder("sudo", nmapPath)
                : new ProcessBuilder(nmapPath);

        processBuilder.command().addAll(command);
        processBuilder.redirectErrorStream(true);

        try {
            logger.info("使用命令启动Nmap进程: {}", processBuilder.command());
            Process process = processBuilder.start();
            Charset charset = os.contains("win") ? Charset.forName("GBK") : Charset.forName("UTF-8");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), charset), 1024)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lineConsumer.accept(line);
                    System.out.flush();  // 确保数据实时输出
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                logger.error("Nmap命令执行失败，退出代码: {}", exitCode);
                throw new Exception("Nmap命令执行失败，退出代码: " + exitCode);
            }
        } catch (Exception e) {
            logger.error("执行Nmap命令时出错: ", e);
            throw new Exception("执行Nmap命令时出错: " + e.getMessage());
        }
    }
}
