package com.example.salesBackend.Service;


import com.example.salesBackend.Entity.DailySchedule;

import com.example.salesBackend.Repo.DailyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public List<DailySchedule> getAllDailySchedules() {
        return dailyScheduleRepository.findAll();
    }

    public Optional<DailySchedule> getDailyScheduleById(Long id) {
        return dailyScheduleRepository.findById(id);
    }

    public List<DailySchedule> getDailySchedulesByAgntnum(String agntnum) {
        return dailyScheduleRepository.findByAgntnum(agntnum);
    }

    public DailySchedule updateDailySchedule(Long id, DailySchedule dailySchedule) {
        Optional<DailySchedule> optionalDailySchedule = dailyScheduleRepository.findById(id);
        if (optionalDailySchedule.isPresent()) {
            DailySchedule existingDailySchedule = optionalDailySchedule.get();
            existingDailySchedule.setAgntnum(dailySchedule.getAgntnum());
            existingDailySchedule.setStartDate(dailySchedule.getStartDate());
            existingDailySchedule.setEndDate(dailySchedule.getEndDate());
            existingDailySchedule.setTITLE(dailySchedule.getTITLE());
            existingDailySchedule.setSTATUS(dailySchedule.getSTATUS());
            existingDailySchedule.setAllDay(dailySchedule.getAllDay());
            return dailyScheduleRepository.save(existingDailySchedule);
        }
        return null;
    }

    public void deleteDailySchedule(Long id) {
        dailyScheduleRepository.deleteById(id);
    }


    public List<Object[]> getTodayEvents(String agntnum) {
        return dailyScheduleRepository.findTodayEvents( agntnum);
    }





}

