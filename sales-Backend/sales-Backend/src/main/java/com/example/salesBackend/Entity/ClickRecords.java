package com.example.salesBackend.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClickRecords {
    @Id
    private long empId;
    private LocalDateTime LoginDate;
    private String category;
    private int PolicyInquiry;
    private int Reports;
    private int Birthdays;
    private int CollectionReports;

}
