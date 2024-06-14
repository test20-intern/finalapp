package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
    List<DailySchedule> findByAgntnum(String agntnum);

    @Query(nativeQuery = true, value = "EXEC SalesApp_Select_TodayEvents  " +
            "@agntnum = :agntnum " )
    List<Object[]> findTodayEvents(
            @Param("agntnum") String agntnum
    );




}
