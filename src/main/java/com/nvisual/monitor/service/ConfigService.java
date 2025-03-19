package com.nvisual.monitor.service;
import com.nvisual.monitor.model.Device;
import com.nvisual.monitor.model.NetworkSegment;
import com.nvisual.monitor.model.Nmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ConfigService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Map<String, String> settings = new HashMap<>();
    @PostConstruct
    public void loadSettings() {
        jdbcTemplate.query("SELECT name, value FROM setting", rs -> {
            settings.put(rs.getString("name"), rs.getString("value"));
        });
    }
    public String getSetting(String name) {
        return settings.get(name);
    }
    public void updateSetting(String name, String value) {
        jdbcTemplate.update("UPDATE setting SET value = ? WHERE name = ?", value, name);
        settings.put(name, value);  // Update the local cache as well
    }
    /**
     * 查找供应商信息通过MAC前缀
     * @param macPrefix MAC地址前缀
     * @return 供应商名称
     */
    public String getVendorByMacPrefix(String macPrefix) {
        String sql = "SELECT vendor FROM mac_vendor WHERE ? LIKE mac || '%'";
        return jdbcTemplate.queryForObject(sql, new Object[]{macPrefix}, String.class);
    }
    /**
     * 获取所有的网段管理的ip地址段
     *
     * @return ip地址段
     */
    public List<String> getAllCidrIps() {
        String sql = "SELECT cidr FROM network_segment";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("cidr");
            }
        });
    }
    /**
     * 获取所有的 network_segment 表数据
     * @return 包含 cidr 和 description 的列表
     */
    public List<NetworkSegment> getAllNetworkSegments() {
        String sql = "SELECT * FROM network_segment";

        return jdbcTemplate.query(sql, new RowMapper<NetworkSegment>() {
            @Override
            public NetworkSegment mapRow(ResultSet rs, int rowNum) throws SQLException {
                String cidr = rs.getString("cidr");
                String description = rs.getString("description");
                return new NetworkSegment(cidr, description);
            }
        });
    }
    /**
     * 新增网段管理
     * @param cidr 网段
     * @param description 描述信息
     */
    public void addNetworkSegment(String cidr, String description) {
        String sql = "INSERT INTO network_segment (cidr, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, cidr, description);
    }

    /**
     * 删除网段管理
     * @param cidr 网段
     */
    public void deleteNetworkSegment(String cidr) {
        String sql = "DELETE FROM network_segment WHERE cidr = ?";
        jdbcTemplate.update(sql, cidr);
    }
    /**
     * 根据cidr查询网段管理
     * @param cidr 网段
     * @return 对应的NetworkSegment对象，若不存在则返回null
     */
    public NetworkSegment getNetworkSegmentByCidr(String cidr) {
        String sql = "SELECT * FROM network_segment WHERE cidr = ?";
        List<NetworkSegment> results = jdbcTemplate.query(sql, new Object[]{cidr}, new RowMapper<NetworkSegment>() {
            @Override
            public NetworkSegment mapRow(ResultSet rs, int rowNum) throws SQLException {
                String foundCidr = rs.getString("cidr");
                String description = rs.getString("description");
                return new NetworkSegment(foundCidr, description);
            }
        });
        return results.isEmpty() ? null : results.get(0);
    }
    // 根据释义获取对应的Nmap指令
    public String getNmapInstruction(String description) {
        String sql = "SELECT instructions FROM nmap WHERE description = ?";
        List<String> instructions = jdbcTemplate.queryForList(sql, new Object[]{description}, String.class);
        return instructions.isEmpty() ? null : instructions.get(0);
    }
    /**
     * 获取所有的 network_segment 表数据
     * @return 包含 cidr 和 description 的列表
     */
    public List<Nmap> getAllNmap() {
        String sql = "SELECT * FROM nmap";

        return jdbcTemplate.query(sql, new RowMapper<Nmap>() {
            @Override
            public Nmap mapRow(ResultSet rs, int rowNum) throws SQLException {
                String instructions = rs.getString("instructions");
                String description = rs.getString("description");
                return new Nmap(instructions, description);
            }
        });
    }
    /**
     * 存储snmp获取到的llpd数据
     */
    public void saveDeviceToDatabase(Device device) {
        String sql = "INSERT INTO device (ip, community, lldp,description,name,objectId) VALUES (?, ?, ?,?, ?, ?)";
        jdbcTemplate.update(sql, device.getIp(), device.getCommunity(), device.getLldp(),device.getDescription(),
                device.getName(),device.getObjectId());
    }
    public Optional<Device> findDeviceByIp(String ipAddress) {
        String sql = "SELECT * FROM device WHERE ip = ?";
        try {
            Device device = jdbcTemplate.queryForObject(sql, new Object[]{ipAddress}, new BeanPropertyRowMapper<>(Device.class));
            return Optional.of(device);
        } catch (EmptyResultDataAccessException e) {
            // 如果查询结果为空，则返回 Optional.empty()
            return Optional.empty();
        }
    }
}
