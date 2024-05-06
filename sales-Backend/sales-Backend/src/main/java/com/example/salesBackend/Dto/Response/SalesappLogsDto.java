package com.example.salesBackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesappLogsDto {
    private long logId;
    private long empId;
    private String userName;
    private String userType;
    private String ipAddress;
    private LocalDateTime logDate;

}
