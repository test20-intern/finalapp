package com.example.salesBackend.Controller;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Service.CLIENTINFOSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/clientinfo")
public class CLIENTINFOCONTROLER {

    @Autowired
    private CLIENTINFOSERVICE CLIENTINFOSERVICE;

    @GetMapping("/getAllClients")
    public List<PG_CLIENTINFO> getAllClients() {

        return CLIENTINFOSERVICE.getAllClients();
    }


}
