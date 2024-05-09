package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Response.SalesappLogsDto;
import com.example.salesBackend.Entity.SalesappLogs;
import com.example.salesBackend.Service.SystemLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("systemLogs")
@CrossOrigin
public class SystemLogsController {

    @Autowired
    private SystemLogsService systemLogsService;

    @PostMapping("/saveLog")
    public String saveSystemLogs(@RequestBody SalesappLogsDto salesappLogsDto) {
        //SalesappLogs systemLogs = mapDtoToEntity(salesappLogsDto);
        return systemLogsService.saveLog(salesappLogsDto);
    }

    private SalesappLogs mapDtoToEntity(SalesappLogsDto salesappLogsDto) {
        SalesappLogs salesappLogs = new SalesappLogs();
        salesappLogs.setEmpId(salesappLogsDto.getEmpId());
        salesappLogs.setUserName(salesappLogsDto.getUserName());
        salesappLogs.setUserType(salesappLogsDto.getUserType());
        salesappLogs.setIpAddress(salesappLogsDto.getIpAddress());
        salesappLogs.setLogDate(salesappLogsDto.getLogDate());
        return salesappLogs;
    }

}
