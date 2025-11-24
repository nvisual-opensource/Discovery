package cn.com.nvisual.monitor.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="topology")
public class Topology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sourceIp")
    private String sourceIp;
    @Column(name = "destinationIp")
    private String destinationIp;
    @Column(name = "lldpData")
    private String lldpData;

    // Getters and setters
}