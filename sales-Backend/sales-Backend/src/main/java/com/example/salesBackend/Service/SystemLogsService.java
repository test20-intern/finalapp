package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Response.SalesappLogsDto;
import com.example.salesBackend.Entity.SalesappLogs;
import com.example.salesBackend.Repo.SystemLogsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SystemLogsService {

    @Autowired
    private SystemLogsRepo systemLogsRepo;

    @Autowired
    private ModelMapper modelMapper;


    public String saveLog(SalesappLogsDto salesappLogsDto){
        SalesappLogs salesappLogs = modelMapper.map(salesappLogsDto, SalesappLogs.class);
        salesappLogs.setLogDate(LocalDateTime.now());
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        systemLogsRepo.save(salesappLogs);
        return "ok";
    }


}
