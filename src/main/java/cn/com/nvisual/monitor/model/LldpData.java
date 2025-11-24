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
public class LldpData {
    Integer num;
    String localHostIp;
    String localHostName;
    String localInterfaceName;
    String localHostType;
    String localHostDescription;
    String localHostOid;
    String localHostOidType;
    String remoteHostIp;
    String remoteHostName;
    String remoteInterfaceName;
    String remoteHostType;
    String remoteHostDescription;
    String remoteHostOid;
    String remoteHostOidType;
}
