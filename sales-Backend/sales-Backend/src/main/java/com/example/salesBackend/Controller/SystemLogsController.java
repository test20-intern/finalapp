package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Response.SystemLogsDto;
import com.example.salesBackend.Entity.SystemLogs;
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
    public String saveSystemLogs(@RequestBody SystemLogsDto systemLogsDto) {
        //SystemLogs systemLogs = mapDtoToEntity(systemLogsDto);
        return systemLogsService.saveLog(systemLogsDto);
    }

    private SystemLogs mapDtoToEntity(SystemLogsDto systemLogsDto) {
        SystemLogs systemLogs = new SystemLogs();
        systemLogs.setEmpId(systemLogsDto.getEmpId());
        systemLogs.setUserName(systemLogsDto.getUserName());
        systemLogs.setUserType(systemLogsDto.getUserType());
        systemLogs.setIpAddress(systemLogsDto.getIpAddress());
        systemLogs.setLogDate(systemLogsDto.getLogDate());
        return systemLogs;
    }

}
