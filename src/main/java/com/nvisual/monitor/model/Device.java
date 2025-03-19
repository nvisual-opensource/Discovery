package com.nvisual.monitor.model;

import lombok.Data;

import javax.persistence.*;
@Data
public class Device {
    private String ip;
    private String community;
    private String lldp;
    private String description;
    private String name;
    private String objectId;
}
