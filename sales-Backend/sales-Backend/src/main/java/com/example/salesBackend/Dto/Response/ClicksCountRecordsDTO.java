package com.example.salesBackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClicksCountRecordsDTO {
    private String empId;
    private String empName;
    private String category;
    private String feature;
}
