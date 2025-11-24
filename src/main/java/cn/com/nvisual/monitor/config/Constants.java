package cn.com.nvisual.monitor.config;
public class Constants {

	// 系统描述
	public static final String sysDescr = ".1.3.6.1.2.1.1.1.0";

	// 系统名称
	public static final String sysName = ".1.3.6.1.2.1.1.5.0";

	// 系统对象 ID
	public static final String sysObjectID = ".1.3.6.1.2.1.1.2.0";

	// 接口数量
	public static final String ifNumber = ".1.3.6.1.2.1.2.1.0";

	// 系统联系人
	public static final String sysContact = ".1.3.6.1.2.1.1.4.0";

	// LLDP 本地主机 ID
	public static final String lldpLocalhostId = ".1.0.8802.1.1.2.1.3.2.0";

	// 系统 IP 地址
	public static final String[] sysIp = new String[]{ ".1.3.6.1.2.1.4.20.1.1" };

	// LLDP 远端设备信息
	public static final String[] ifOidslldp = new String[]{
			"1.0.8802.1.1.2.1.4.1.1.7",  // 远端设备端口号
			"1.0.8802.1.1.2.1.4.1.1.8",  // 远端设备端口描述
			"1.0.8802.1.1.2.1.4.1.1.9",  // 远端设备名称
			"1.0.8802.1.1.2.1.4.1.1.10", // 远端设备描述
			".1.0.8802.1.1.2.1.4.1.1.5", // 远端设备端口 MAC
			".1.0.8802.1.1.2.1.4.1.1.6"  // 远端设备端口类型，包括接口名称和 MAC 地址
	};

	// 获取各端口 IP 地址
	public static final String[] ifIpAddr = new String[]{
			".1.3.6.1.2.1.4.20.1.1", // IP 地址
			".1.3.6.1.2.1.4.20.1.2", // 端口索引
			".1.3.6.1.2.1.4.20.1.3", // IP 地址掩码
			".1.3.6.1.2.1.4.20.1.4", // 允许广播地址
			".1.3.6.1.2.1.4.20.1.5"  // IP 最大包字节数
	};

	// 设备上的 Trunk 与物理口的对应关系
	public static final String[] ifTrunkInterfaceOids = new String[]{
			".1.0.8802.1.1.2.1.3.7.1.3",  // 端口名称
			".1.0.8802.1.1.2.1.5.4623.1.2.3.1.2", // 属于哪个聚合口
			".1.0.8802.1.1.2.1.5.4623.1.2.3.1.1" // 当前的聚合状态，即是否属于聚合口
	};

	// 设备上的 VLAN 与物理口的对应关系
	public static final String[] ifVlanInterfaceOids = new String[]{
			"1.3.6.1.2.1.17.7.1.4.5.1.1" // if 与 VLAN 对应关系
	};

	// 设备上的 VLAN 与物理口的对应关系（备用）
	public static final String[] ifVlanInterfaceOids2 = new String[]{
			".1.3.6.1.2.1.17.7.1.4.2.1.3", // VLAN ID
			".1.3.6.1.2.1.17.7.1.4.2.1.4" // 物理口 ID
	};

	// ARP 表
	public static final String[] arpOids = new String[]{
			".1.3.6.1.2.1.4.22.1.1", // 接口索引，交换机则是 VLAN
			".1.3.6.1.2.1.4.22.1.2", // ARP MAC 地址
			".1.3.6.1.2.1.4.22.1.3", // ARP IP 地址
			".1.3.6.1.2.1.4.22.1.4"  // 静态或动态
	};

	// MAC 转发表
	public static final String[] FdbOids = new String[]{
			".1.3.6.1.2.1.17.4.3.1.1", // MAC 地址
			".1.3.6.1.2.1.17.4.3.1.2", // "="等号左边是 VLAN 和 10 进制 MAC，右边是端口 ID
			".1.3.6.1.2.1.17.4.3.1.3"  // 状态: other(1), invalid(2), learned(3), self(4), mgmt(5)
	};

	// MAC 转发表 2，华为 7506X 不支持第一种，可以用这个
	public static final String[] FdbOids2 = new String[]{
			".1.3.6.1.2.1.17.7.1.2.2.1.1", // MAC 地址，实际可能是空的
			".1.3.6.1.2.1.17.7.1.2.2.1.2", // "="等号左边是 VLAN 和 10 进制 MAC，右边是端口 ID
			".1.3.6.1.2.1.17.7.1.2.2.1.3"  // 状态: other(1), invalid(2), learned(3), self(4), mgmt(5)
	};

	// 配合 FdbOids 和 FdbOids2 使用，获取端口 ifIndex
	public static final String[] FdbOidsPortIfIndex = new String[]{
			".1.3.6.1.2.1.17.1.4.1.1", // 转发端口 ID
			".1.3.6.1.2.1.17.1.4.1.2"  // 转发端口 ifIndex
	};

	// MAC 转发表 3，中兴设备仅支持通过这个方式获取
	// 示例: .1.3.6.1.4.1.3902.3.6000.500.2.6.1.3.6.(160.140.248.60.176.149)mac.(2170)vlan = gei-0/5/0/40
	public static final String[] FdbOids3 = new String[]{
			".1.3.6.1.4.1.3902.3.6000.500.2.6.1.3"
	};
}
