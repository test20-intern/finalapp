package com.example.salesBackend.Service;


import com.example.salesBackend.Entity.DailySchedule;

import com.example.salesBackend.Repo.DailyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
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



    @Transactional
    public void updateDailyDiary(String agntnum, LocalDateTime startDate, LocalDateTime endDate, String newTitle) {
        try {
            int rowsAffected = dailyScheduleRepository.updateDailySchedule(agntnum, startDate, endDate, newTitle);
            if (rowsAffected == 0) {
                throw new RuntimeException("No rows updated");
            }
        } catch (Exception e) {

            throw new RuntimeException("Failed to update daily diary", e);
        }
    }



}

