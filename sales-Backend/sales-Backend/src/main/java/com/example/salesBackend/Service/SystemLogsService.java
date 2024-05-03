package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Response.SystemLogsDto;
import com.example.salesBackend.Entity.SystemLogs;
import com.example.salesBackend.Repo.SystemLogsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class SystemLogsService {

    @Autowired
    private SystemLogsRepo systemLogsRepo;

    @Autowired
    private ModelMapper modelMapper;


    public String saveLog(SystemLogsDto systemLogsDto){
        SystemLogs systemLogs = modelMapper.map(systemLogsDto,SystemLogs.class);
        systemLogs.setLogDate(LocalDateTime.now());
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        systemLogsRepo.save(systemLogs);
        return "ok";
    }


}
