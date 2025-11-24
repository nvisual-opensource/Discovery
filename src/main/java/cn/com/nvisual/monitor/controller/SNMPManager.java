package cn.com.nvisual.monitor.controller;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SNMPManager {

    // 交换机的IP地址
    private static String ipAddress = "192.168.100.189";
    // SNMP的团体字
    private static String community = "nvisual";
    // FDB表的OID（这个OID用于获取交换机转发数据库表的信息）
    private static String fdbOid = ".1.3.6.1.2.1.17.4.3.1"; // 这是一个示例OID，实际OID可能会根据交换机不同而有所变化
    private static String fdbOid2 = ".1.3.6.1.2.1.17.7.1.2.2.1.1"; // 这是一个示例OID，实际OID可能会根据交换机不同而有所变化
    private static String fdbOid3 = ".1.3.6.1.4.1.3902.3.6000.500.2.6.1.3"; // 这是一个示例OID，实际OID可能会根据交换机不同而有所变化
    private static String fdbOid4 = ".1.3.6.1.2.1.1.5.0"; // 这是一个示例OID，实际OID可能会根据交换机不同而有所变化

    public static void main(String[] args) {
        try {
            // 创建一个SNMPManager实例
            SNMPManager manager = new SNMPManager();
            // 获取交换机的FDB表数据（即转发数据库）
            List<String> fdbEntries = manager.getFDBTable(ipAddress, community, fdbOid);
            // 打印返回的FDB表条目
            System.err.println("FDB Entries: " + fdbEntries);
            List<String> fdbEntries2 = manager.getFDBTable(ipAddress, community, fdbOid2);
            // 打印返回的FDB表条目
            System.err.println("FDB Entries2: " + fdbEntries2);
            List<String> fdbEntries3 = manager.getFDBTable(ipAddress, community, fdbOid3);
            // 打印返回的FDB表条目
            System.err.println("FDB Entries3: " + fdbEntries3);
            List<String> fdbEntries4 = manager.getFDBTable(ipAddress, community, fdbOid4);
            // 打印返回的FDB表条目
            System.err.println("FDB Entries4: " + fdbEntries4);
        } catch (IOException e) {
            // 捕获异常并打印错误信息
            e.printStackTrace();
        }
    }

    /**
     * 获取交换机的FDB表（转发数据库）
     * @param ipAddress 交换机的IP地址
     * @param community SNMP团体字
     * @param oid FDB表的OID
     * @return 包含FDB表信息的列表
     * @throws IOException SNMP操作过程中可能抛出的异常
     */
    public List<String> getFDBTable(String ipAddress, String community, String oid) throws IOException {
        // 创建UDP传输映射，用于传输SNMP数据包
        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        // 创建SNMP对象
        Snmp snmp = new Snmp(transport);
        // 启动监听传输
        transport.listen();

        // 创建SNMP目标对象，指定交换机的地址和SNMP团体字
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));  // 设置团体字
        target.setAddress(new UdpAddress(ipAddress + "/161"));  // 设置交换机的IP地址和端口（默认端口161）
        target.setRetries(2);  // 设置重试次数
        target.setTimeout(1500);  // 设置超时时间，单位毫秒
        target.setVersion(SnmpConstants.version2c);  // 使用SNMP v2c协议

        // 创建PDU（协议数据单元），指定PDU的类型为GETBULK，表示获取大量数据
        PDU pdu = new PDU();
        pdu.setType(PDU.GETBULK);  // 设置PDU类型为GETBULK，适合请求多个条目
        pdu.setMaxRepetitions(10);  // 每次请求最大返回10个条目
        pdu.setNonRepeaters(0);  // 没有非重复项
        pdu.add(new VariableBinding(new OID(oid)));  // 将指定的OID添加到PDU中，这里是FDB表的OID

        // 创建一个列表，用于保存获取到的FDB表条目
        List<String> fdbEntries = new ArrayList<>();
        // 设置一个标志变量来控制获取数据的循环
        boolean finished = false;

        System.out.println("发送SNMP请求获取FDB表数据...");

        // 循环请求FDB表数据，直到获取完所有数据
        while (!finished) {
            // 发送SNMP请求并等待响应
            System.out.println("发送PDU请求，OID: " + oid);
            ResponseEvent responseEvent = snmp.send(pdu, target);
            PDU responsePDU = responseEvent.getResponse();  // 获取响应PDU

            // 如果响应为空，则表示发生了错误，或者交换机未响应
            if (responsePDU == null) {
                System.out.println("未收到响应或响应为空。");
                break;  // 退出循环
            }

            System.out.println("收到SNMP响应，包含 " + responsePDU.getVariableBindings().size() + " 个变量绑定条目");

            // 遍历响应中的所有变量绑定（VariableBinding），它们包含了从交换机返回的数据
            for (VariableBinding vb : responsePDU.getVariableBindings()) {
                System.out.println("处理OID: " + vb.getOid() + ", 值: " + vb.getVariable());

                // 如果返回的OID以FDB表的OID为前缀，则是我们需要的FDB数据
                if (vb.getOid().startsWith(new OID(oid))) {
                    fdbEntries.add(vb.toString());  // 将返回的变量绑定信息（FDB条目）加入列表
                } else {
                    finished = true;  // 如果OID不再是FDB表的OID，表示数据获取完成，跳出循环
                    System.out.println("遇到非FDB条目，结束数据获取。");
                }
            }

            // 如果响应中返回的条目少于请求的最大数量，说明已经获取到所有条目
            if (responsePDU.getVariableBindings().size() < 10) {
                finished = true;
                System.out.println("响应的条目数少于最大重复次数，认为已获取所有数据。");
            }
        }

        // 关闭SNMP连接
        snmp.close();

        System.out.println("FDB表获取完成，条目数量: " + fdbEntries.size());

        // 返回获取到的FDB表条目
        return fdbEntries;
    }
}
