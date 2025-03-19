package com.nvisual.monitor.model;

import lombok.Data;

import java.util.Date;

/**
 * @Classname Alerts
 * @Description TODO
 * @Date 2024/9/5 19:36
 * @Created by junjie.dong
 */
@Data
public class IpNode {
    String ip;
    String hostName;
    String pingState;
    String macAddress;
    String type;
    String vendor;
    String date;
}
