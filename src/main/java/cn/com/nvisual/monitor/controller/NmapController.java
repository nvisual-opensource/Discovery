package cn.com.nvisual.monitor.controller;
import cn.com.nvisual.monitor.model.Nmap;
import cn.com.nvisual.monitor.service.ConfigService;
import cn.com.nvisual.monitor.service.NmapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/wapi/v1")
public class NmapController {
    @Autowired
    private NmapService nmapService;
    @Autowired
    ConfigService configService;
// 创建一个线程池来处理多线程任务，防止阻塞主线程
private final ExecutorService executor = Executors.newCachedThreadPool();
    private static final Logger logger = LoggerFactory.getLogger(NmapController.class);

    @RequestMapping(value = "/nmap/run", method = RequestMethod.GET)
    public ResponseEntity<SseEmitter> runNmap(@RequestParam String ip, @RequestParam String description) {
        // 检查输入参数
        if (ip == null || ip.isEmpty() || description == null || description.isEmpty()) {
            logger.error("IP or description parameter is empty");
            return ResponseEntity.badRequest().body(null); // 返回400错误
        }

        // 设置SseEmitter的超时时间
        SseEmitter emitter = new SseEmitter(3600_000L); // 1小时超时

        // 异步执行Nmap命令并发送结果
        executor.submit(() -> {
            try {
                // 获取Nmap指令
                String instruction = configService.getNmapInstruction(description);
                if (instruction == null || instruction.isEmpty()) {
                    throw new Exception("Nmap instruction is empty");
                }

                instruction = instruction.replace("[target]", ip);
                logger.info("Executing Nmap instruction: {}", instruction);

                // 调用Nmap命令
                nmapService.executeNmapCommand(instruction, line -> {
                    try {
                        // 仅当行不为空时发送数据，避免冗余空行
                        if (!line.trim().isEmpty()) {
                            emitter.send(line, MediaType.TEXT_PLAIN);
                        }
                    } catch (IllegalStateException e) {
                        // 捕获SseEmitter已经关闭的异常
                        logger.error("SseEmitter is turned off, unable to send data: ", e);
                    } catch (Exception e) {
                        // 处理其他可能的异常
                        logger.error("Error sending data to SseEmitter: ", e);
                        emitter.completeWithError(e); // 出错时关闭连接
                    }
                });

                // 完成数据发送，关闭SSE连接
                emitter.complete();
            } catch (Exception e) {
                logger.error("Error executing Nmap: ", e);
                try {
                    emitter.send("Nmap not installed or path not found", MediaType.TEXT_PLAIN); // 返回特定中文信息给前端
                } catch (Exception sendError) {
                    logger.error("Error sending error message to SseEmitter: ", sendError);
                }
                emitter.completeWithError(e); // 异常处理
            }
        });

        return ResponseEntity.ok(emitter); // 返回200状态
    }
    //入网监测,获取网段设置列表
    @RequestMapping(value = "/nmap/get_all", method = RequestMethod.GET)
    public List<Nmap> getnmapAll(HttpServletResponse httpServletResponse) {
        List<Nmap> allNmap = configService.getAllNmap();
        return allNmap;
    }
}