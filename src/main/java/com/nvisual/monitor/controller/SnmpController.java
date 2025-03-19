package com.nvisual.monitor.controller;

import com.nvisual.monitor.model.SnmpDiscover;
import com.nvisual.monitor.service.SnmpDiscoveryService;
/*
import com.nvisual.monitor.service.TopologyService;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/wapi/v1")
public class SnmpController {
    @Autowired
    private SnmpDiscoveryService snmpDiscoveryService;


    @RequestMapping(value = "/snmp/discover", method = RequestMethod.POST)
    public String discoverDevices(@RequestBody SnmpDiscover snmpDiscover) throws IOException {
        snmpDiscoveryService.discoverDevices(snmpDiscover.getIpRange(), snmpDiscover.getCommunities());
        return "Device discovery started!";
    }

}
