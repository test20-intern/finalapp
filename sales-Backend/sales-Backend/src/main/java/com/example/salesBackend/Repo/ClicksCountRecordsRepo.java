package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.ClicksCountRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClicksCountRecordsRepo extends JpaRepository <ClicksCountRecords, Long> {
    ClicksCountRecords findByEmpIdAndLoginDate(String empId, LocalDate today);
}
