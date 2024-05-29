package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {

    List<DailySchedule> findByagntnum(String agntnum);
    //Optional<DailySchedule> findByAgntnumAndStartDateAndEndDate(String agntnum, Date START_DATE, Date END_DATE);
}
