package com.example.salesBackend.Dto.Response;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClickRecordsDTO {

    private long empId;
    private LocalDateTime LoginDate;
    private String category;
    private int PolicyInquiry;
    private int Reports;
    private int Birthdays;
    private int CollectionReports;
}
