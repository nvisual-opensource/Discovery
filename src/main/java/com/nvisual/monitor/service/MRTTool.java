package com.nvisual.monitor.service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.nio.charset.Charset;

//链路追踪 tracer route

public class MRTTool {

    public static void main(String[] args) {
        String hostname = "8.8.8.8";
//        if (args.length != 1) {
//            System.err.println("Usage: java MRTTool <hostname>");
//            System.exit(1);
//        }

        //String hostname = args[0];

        try {
            traceRoute(hostname);
        } catch (IOException e) {
            System.err.println("Failed to execute traceroute: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void traceRoute(String host) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder;

        if (os.contains("win")) {
            // Windows uses 'tracert' with '-d' to disable DNS resolution
            processBuilder = new ProcessBuilder("tracert", "-d", host);
        } else {
            // Unix-based systems use 'traceroute' with '-n' to disable DNS resolution
            processBuilder = new ProcessBuilder("traceroute", "-n", host);
        }

        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        // Use GBK charset for Windows, UTF-8 for others
        Charset charset = os.contains("win") ? Charset.forName("GBK") : Charset.forName("UTF-8");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), charset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Traceroute command exited with non-zero status: " + exitCode);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Traceroute command was interrupted", e);
        }
    }
}
