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

//    List<DailySchedule> findByagntnum(String agntnum);
//
//
//    @Modifying
//    @Query(value = "EXEC SalesApp_Update_DailyScheduleTitle @agntnum = :agntnum, @startDate= :startDate, @endDate= :endDate, @newTitle= :newTitle", nativeQuery = true)
//    int updateDailySchedule(
//            @Param("agntnum") String agntnum,
//            @Param("startDate") LocalDateTime startDate,
//            @Param("endDate") LocalDateTime endDate,
//            @Param("newTitle") String newTitle
//    );


}
