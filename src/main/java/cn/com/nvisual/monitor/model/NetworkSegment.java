package cn.com.nvisual.monitor.model;
public class NetworkSegment {
    private String cidr;
    private String description;

    // 构造函数
    public NetworkSegment(String cidr, String description) {
        this.cidr = cidr;
        this.description = description;
    }

    // Getter 和 Setter 方法
    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NetworkSegment [cidr=" + cidr + ", description=" + description + "]";
    }
}
