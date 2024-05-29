package com.example.salesBackend.Service;


import com.example.salesBackend.Entity.DailySchedule;

import com.example.salesBackend.Repo.DailyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyScheduleService {

    @Autowired
    private DailyScheduleRepository dailyScheduleRepository;

    public DailySchedule saveDailySchedule(DailySchedule dailySchedule) {
        return dailyScheduleRepository.save(dailySchedule);
    }

    public List<DailySchedule> getDailySchedulesByagntnum(String agntnum) {
        return dailyScheduleRepository.findByagntnum(agntnum);
    }


}

