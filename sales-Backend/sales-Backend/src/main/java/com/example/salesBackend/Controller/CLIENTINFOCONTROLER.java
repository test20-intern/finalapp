package com.example.salesBackend.Controller;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Service.CLIENTINFOSERVICE;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/clientinfo")
public class CLIENTINFOCONTROLER {

    @Autowired
    private CLIENTINFOSERVICE clientInfoService;

    @Autowired
    private POLICYINFOSERVICE policyInfoService;

    @GetMapping("/getAllClients")
    public List<PG_CLIENTINFO> getAllClients() {
        return clientInfoService.getAllClients();
    }




}