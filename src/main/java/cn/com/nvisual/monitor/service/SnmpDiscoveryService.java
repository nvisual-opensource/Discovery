package cn.com.nvisual.monitor.service;

import cn.com.nvisual.monitor.model.Device;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.io.IOException;

import java.util.*;

@Service
public class SnmpDiscoveryService {

    @Autowired
    ConfigService configService;
    @Autowired
    IpUtil ipUtil;

    private static final int THREAD_POOL_SIZE = 10;

    public void discoverDevices(String ipRange, List<String> communityStrings) {
        System.err.println("communityStrings：" + communityStrings);
        // 获取IP地址范围内的所有IP地址
        List<String> ipAddresses = getIpRange(ipRange);

        for (String ipAddress : ipAddresses) {
            System.err.println("ipAddress:"+ipAddress);
            for (String community : communityStrings) {
                try {
                    // 使用 Optional 处理设备查询
                    Optional<Device> existingDevice = configService.findDeviceByIp(ipAddress);
                    if (existingDevice.isPresent()) {
                        System.out.println("设备已存在，跳过: " + ipAddress);
                        continue;
                    }

                    int i = 1;
                    i++;
                    // 创建一个SNMP实例，并启动SNMP监听，准备发送请求。
                    Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
                    snmp.listen();

                    // 创建一个SNMP目标（Target），用于指定要进行SNMP请求的目标设备。
                    CommunityTarget target = new CommunityTarget();
                    target.setCommunity(new OctetString(community));
                    target.setAddress(new UdpAddress(ipAddress + "/161"));
                    target.setTimeout(1000);
                    target.setRetries(2);

                    // 创建一个SNMP的PDU（Protocol Data Unit），它用于封装SNMP请求的数据。
                    PDU pdu = new PDU();
                    pdu.add(new VariableBinding(new OID(Constants.sysDescr)));  // 系统描述
                    pdu.add(new VariableBinding(new OID(Constants.sysName)));  // 系统名称
                    pdu.add(new VariableBinding(new OID(Constants.sysObjectID)));  // 系统对象 ID
                    pdu.add(new VariableBinding(new OID(Constants.ifNumber)));  // 接口数量
                    pdu.add(new VariableBinding(new OID(Constants.sysContact)));  // 系统联系人
                    pdu.add(new VariableBinding(new OID(Constants.lldpLocalhostId)));  // LLDP 本地主机 ID
                    pdu.setType(PDU.GET);

                    // 发送SNMP请求，并处理响应。
                    Map<String, String> responseMap = sendSnmpRequest(snmp, target, pdu);

                    for (Map.Entry<String, String> entry : responseMap.entrySet()) {
                        System.err.println("responseMap::: " + entry.getKey() + ": " + entry.getValue());
                    }
                    if (!responseMap.isEmpty()) {
                        Device device = new Device();
                        device.setIp(ipAddress);
                        device.setCommunity(community);
                        device.setLldp(getLldpData(ipAddress, community));

                        device.setDescription(responseMap.get(Constants.sysDescr));
                        device.setName(responseMap.get(Constants.sysName));
                        device.setObjectId(responseMap.get(Constants.sysObjectID));

                        configService.saveDeviceToDatabase(device);
                    }

                    snmp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public Map<String, String> sendSnmpRequest(Snmp snmp, CommunityTarget target, PDU pdu) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            ResponseEvent responseEvent = snmp.send(pdu, target);
            PDU responsePDU = responseEvent.getResponse();
            if (responsePDU != null) {
                for (VariableBinding vb : responsePDU.getVariableBindings()) {
                    responseMap.put(vb.getOid().toString(), vb.getVariable().toString());
                }
            } else {
                System.out.println("Request timed out or no response.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseMap;
    }

    public String getLldpData(String ipAddress, String community) {
        try {
            Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
            snmp.listen();

            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(new UdpAddress(ipAddress + "/161"));
            target.setTimeout(1000);
            target.setRetries(2);

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(".1.0.8802.1.1.2.1.3.1.0")));
            pdu.setType(PDU.GET);

            ResponseEvent responseEvent = snmp.send(pdu, target);
            PDU responsePDU = responseEvent.getResponse();
            if (responsePDU != null) {
                return responsePDU.getVariableBindings().toString();
            } else {
                System.out.println("No response from device.");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> getIpRange(String cidr) {
        System.err.println("cidr：" + cidr);
        List<String> ips = new ArrayList<>();
        if (cidr.contains("-")) {
            final String s = cidr.split("-")[1];
            if (s.contains(".")) {
                ips = ipUtil.parseRange(cidr);
            } else {
                ips = ipUtil.parseRange3(cidr);
            }
        } else if (cidr.contains("/")) {
            ips = ipUtil.parseIpMaskRange2(cidr);
        } else {
            ips.add(cidr);
        }
        return ips;
    }


    public static void main(String[] args) {
        String ipRange = "192.168.100.1/23";
        List<String> communityStrings = Arrays.asList("nvisual");

        SnmpDiscoveryService mainInstance = new SnmpDiscoveryService();
        mainInstance.discoverDevices(ipRange, communityStrings);
    }
}
