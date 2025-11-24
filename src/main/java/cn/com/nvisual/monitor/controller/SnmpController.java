package cn.com.nvisual.monitor.controller;

import cn.com.nvisual.monitor.service.SnmpDiscoveryService;
import cn.com.nvisual.monitor.model.SnmpDiscover;
/*
import com.nvisual.monitor.service.TopologyService;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
