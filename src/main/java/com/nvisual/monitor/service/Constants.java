package com.nvisual.monitor.service;

public class Constants {
    public static final String sysDescr = ".1.3.6.1.2.1.1.1.0";
    public static final String sysName = ".1.3.6.1.2.1.1.5.0";
    public static final String sysObjectID = ".1.3.6.1.2.1.1.2.0";//作为state
    public static final String ifNumber = ".1.3.6.1.2.1.2.1.0";
    public static final String sysContact = ".1.3.6.1.2.1.1.4.0";
    public static final String lldpLocalhostId =".1.0.8802.1.1.2.1.3.2.0";
    public static final String[] sysIp =new String[]{".1.3.6.1.2.1.4.20.1.1"};
    public static final String[] ifOidslldp=new String[]{
    		"1.0.8802.1.1.2.1.4.1.1.7",// 远端设备端口号
    		"1.0.8802.1.1.2.1.4.1.1.8",// 远端设备端口描述
    		"1.0.8802.1.1.2.1.4.1.1.9",// 远端设备名称
    		"1.0.8802.1.1.2.1.4.1.1.10",// 远端设备描述
    		".1.0.8802.1.1.2.1.4.1.1.5",//远端设备端口mac
    		".1.0.8802.1.1.2.1.4.1.1.6"  //远端设备端口类型，包括interfacename和macAddress

    };
    //获取各端口ip地址
    public static final String[] ifIpAddr=new String[]{
    		".1.3.6.1.2.1.4.20.1.1", //IP地址
    		".1.3.6.1.2.1.4.20.1.2",//端口索引
    		".1.3.6.1.2.1.4.20.1.3",//IP地址掩码
    		/**
    		 *  The value of the least-significant bit in the IP
				broadcast address used for sending datagrams on
				the (logical) interface associated with the IP
				address of this entry.  For example, when the
				Internet standard all-ones broadcast address is
				used, the value will be 1.  This value applies to
				both the subnet and network broadcasts addresses
				used by the entity on this (logical) interface.
    		 */
    		".1.3.6.1.2.1.4.20.1.4",//允许广播地址
    		/*Name	 ipAdEntReasmMaxSize
    		 * The size of the largest IP datagram which this
    		entity can re-assemble from incoming IP fragmented
    		datagrams received on this interface.*/
    		".1.3.6.1.2.1.4.20.1.5",//ip最大包字节数

    };
    //配合获取端口IP地址功能，将端口索引转化为名称，并获取状态等其他信息
   /* public static final String[] ifIpAddr_ifOids=new String[]{
    	    ".1.3.6.1.2.1.2.2.1.1",   //设备端口索引号
    	    ".1.3.6.1.2.1.31.1.1.1.1",// ".1.3.6.1.2.1.2.2.1.2",     //端口名称	
    	    ".1.3.6.1.2.1.2.2.1.7",    //端口状态，可读可写
    	    ".1.3.6.1.2.1.2.2.1.8",    //协议状态
    	    ".1.3.6.1.2.1.2.2.1.6",    //端口地址
    	    ".1.3.6.1.2.1.31.1.1.1.18",     //,可读可写 备用端口描述
            ".1.3.6.1.2.1.2.2.1.2"//端口描述  
    };*/
    
  //设备上的trunk与物理口的对应关系
    public static final String[] ifTrunkInterfaceOids=new String[]{
    		".1.0.8802.1.1.2.1.3.7.1.3",//端口名称
    		".1.0.8802.1.1.2.1.5.4623.1.2.3.1.2" , //属于那个聚合口
    		".1.0.8802.1.1.2.1.5.4623.1.2.3.1.1"// 当前的聚合状态，即是否属于聚合口
    };
    //设备上的vlan与物理口的对应关系
    public static final String[] ifVlanInterfaceOids=new String[]{
    		"1.3.6.1.2.1.17.7.1.4.5.1.1",//if与vlan对应关系
    };
    
    //设备上的vlan与物理口的对应关系（备用）
    public static final String[] ifVlanInterfaceOids2=new String[]{
    		".1.3.6.1.2.1.17.7.1.4.2.1.3",//vlan id
    		".1.3.6.1.2.1.17.7.1.4.2.1.4"//物理口id   10.169.63.126
    };
    
    
    //用于监测流量
    public static final String[] ifOids=new String[]{
        ".1.3.6.1.2.1.2.2.1.1",   //设备端口索引号
       ".1.3.6.1.2.1.31.1.1.1.1",// ".1.3.6.1.2.1.2.2.1.2",     //端口名称
        ".1.3.6.1.2.1.2.2.1.7",    //端口状态，可读可写
       ".1.3.6.1.2.1.2.2.1.8",    //协议状态
        ".1.3.6.1.2.1.2.2.1.13",   //入方向丢弃的报文，buffer满
        ".1.3.6.1.2.1.2.2.1.14",   //入方向出错的报文
      ".1.3.6.1.2.1.2.2.1.15",   //未知或者不支持的协议的报文
         ".1.3.6.1.2.1.2.2.1.19",   //出方向丢弃的报文，buffer满
        ".1.3.6.1.2.1.2.2.1.20",   //由于错误无法输出的报文
      ".1.3.6.1.2.1.2.2.1.21",   //输出报文队列长度
        "1.3.6.1.2.1.31.1.1.1.6",    //如方向总字节数
        "1.3.6.1.2.1.31.1.1.1.7",    //入方向总包数
        "1.3.6.1.2.1.31.1.1.1.10",    //出方向总字节数
        "1.3.6.1.2.1.31.1.1.1.11",    //出方向总包数
        ".1.3.6.1.2.1.31.1.1.1.18",     //,可读可写 备用端口描述
        ".1.3.6.1.2.1.2.2.1.2",//端口描述  
        ".1.3.6.1.2.1.2.2.1.3",//端口类型
        "1.3.6.1.2.1.2.2.1.6",  //mac地址
        "1.3.6.1.2.1.2.2.1.5", //ifspeed
         ".1.3.6.1.2.1.2.2.1.2" ,    //端口名称(备用)
        ".1.3.6.1.2.1.2.2.1.4"//mtu

    };

    public static final String[] arpOids=new String[] {
    		".1.3.6.1.2.1.4.22.1.1",//interface index,交换机则是vlan
    		".1.3.6.1.2.1.4.22.1.2",//arp mac address
    		".1.3.6.1.2.1.4.22.1.3",// arp ip address
    	    ".1.3.6.1.2.1.4.22.1.4", // static or dynamic
    };
    
    //mac转发表
    public static final String[] FdbOids=new String[] {
    		".1.3.6.1.2.1.17.4.3.1.1",//mac地址
    		".1.3.6.1.2.1.17.4.3.1.2",//  "="等号左边是vlan和10进制mac，右边是端口端口id
    		".1.3.6.1.2.1.17.4.3.1.3"//状态:other(1),invalid(2),learned(3),self(4),mgmt(5)
    };
  
   //mac转发表2,华三7506x不支持第一种，可以用这个
    public static final String[] FdbOids2=new String[] {
    		".1.3.6.1.2.1.17.7.1.2.2.1.1",//mac地址，实际可能是空的
    		".1.3.6.1.2.1.17.7.1.2.2.1.2",//  "="等号左边是vlan和10进制mac，右边是端口端口id
    		".1.3.6.1.2.1.17.7.1.2.2.1.3"//状态:other(1),invalid(2),learned(3),self(4),mgmt(5)
    };
 
    //配合FdbOids和FdbOids2使用，获取端口ifIndex
    public static final String[] FdbOidsPortIfIndex=new String[] {
    ".1.3.6.1.2.1.17.1.4.1.1",//转发端口id
    ".1.3.6.1.2.1.17.1.4.1.2"//转发端口ifIndex
    };
    
    //mac转发表3，中兴设备仅支持通过这个方式获取
    //示例 .1.3.6.1.4.1.3902.3.6000.500.2.6.1.3.6.（160.140.248.60.176.149）mac.（2170）vlan= gei-0/5/0/40
    public static final String[] FdbOids3=new String[] {
    		".1.3.6.1.4.1.3902.3.6000.500.2.6.1.3"
    };
    
    
    /*public static final String[] arpOid_hw=new String[] {
    		".1.3.6.1.4.1.2011.5.25.123.1.17.1.11", //arp mac address(动态arp)
    		".1.3.6.1.4.1.2011.5.25.123.1.17.1.14",  //interface index，交换机也是端口号（动态arp）
    		".1.3.6.1.4.1.2011.5.25.123.1.18.1.2", //arp mac address(动态arp)
    		".1.3.6.1.4.1.2011.5.25.123.1.18.1.13"  //interface index，交换机也是端口号（动态arp）
    };*/
    //锐捷交换机无法获取物理接口，回去的都是VLAN接口
    //锐捷测试http://10.208.130.182:7080/wlpz/arp?ip=10.177.255.123
    
    
    //中兴10.184.255.254
    /*1.3.6.1.4.1.3902.3.326.7		
		1.3.6.1.4.1.3902.3.326.7.1	"{zxr10ArpItemIfIndex, zxr10ArpItemIPAddress}"	arp条目表
		1.3.6.1.4.1.3902.3.326.7.1.1	Y	学习的arp条目的接口索引   ------只有VLAN  没有物理接口
		1.3.6.1.4.1.3902.3.326.7.1.2	Y	学习的arp条目IP地址
		1.3.6.1.4.1.3902.3.326.7.1.3	N	学习的arp条目的接口索引 ------只有VLAN  没有物理接口
		1.3.6.1.4.1.3902.3.326.7.1.4	N	学习的arp条目的类型
		1.3.6.1.4.1.3902.3.326.7.1.5	N	学习的arp条目的MAC地址
		1.3.6.1.4.1.3902.3.326.7.1.6	N	学习的arp条目的内层VLAN
		1.3.6.1.4.1.3902.3.326.7.1.7	N	学习的arp条目的外层VLAN
		1.3.6.1.4.1.3902.3.326.7.1.8	N	学习的arp条目的子接口名-----物理接口
		1.3.6.1.4.1.3902.3.326.7.1.9	N	学习的arp条目的VTEP接口名
		1.3.6.1.4.1.3902.3.326.7.1.10	N	学习的arp条目的VXLAN ID
		1.3.6.1.4.1.3902.3.326.7.1.11	N	学习的arp条目的远端IP地址

     */
    
    /**
     * 10.208.255.193
     */
    /**
     * 迪普市面产品都不具备，可以开发X.208.255.182
     */
    
    
}
