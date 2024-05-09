package com.example.salesBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClicksCountRecords {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long recordId;
    private String empId;
    private String empName;
    private String category;
    private int policyInquiry;
    private int reports;
    private int collectionReports;
    private int birthday;
    private int dashboard;
    private LocalDate loginDate;

}
