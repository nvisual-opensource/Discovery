package cn.com.nvisual.monitor.model;
import lombok.Data;
import java.util.List;
@Data
public class SnmpDiscover {
    String ipRange;
    List<String> communities;
}
