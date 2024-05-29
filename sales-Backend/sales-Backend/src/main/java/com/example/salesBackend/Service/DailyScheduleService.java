package com.example.salesBackend.Service;


import com.example.salesBackend.Entity.DailySchedule;

import com.example.salesBackend.Repo.DailyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

//    public DailySchedule updateDailySchedule(String agntnum, Date startDate, Date endDate, DailySchedule updatedSchedule) {
//        Optional<DailySchedule> existingSchedule = dailyScheduleRepository.findByAgntnumAndStartDateAndEndDate(agntnum, startDate, endDate);
//
//        if (existingSchedule.isPresent()) {
//            DailySchedule schedule = existingSchedule.get();
//            schedule.setTITLE(updatedSchedule.getTITLE());
//            schedule.setSTATUS(updatedSchedule.getSTATUS());
//            return dailyScheduleRepository.save(schedule);
//        } else {
//            return null;
//        }
//    }


}

