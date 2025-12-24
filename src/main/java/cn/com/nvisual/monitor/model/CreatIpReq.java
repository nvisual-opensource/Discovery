package cn.com.nvisual.monitor.model;

import lombok.Data;

import java.util.List;

/**
 * @Classname Alerts
 * @Description TODO
 * @Date 2024/9/5 19:36
 * @Created by junjie.dong
 */
@Data
public class CreatIpReq {
    Long diagramId;
    String typeName;
    List<IpAndName> ipAndName;
    Integer unit;
    String direction;
    String job;
}
