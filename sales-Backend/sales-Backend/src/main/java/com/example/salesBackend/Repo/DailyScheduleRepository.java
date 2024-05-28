package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
}
