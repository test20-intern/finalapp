package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesappLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;
    private String empId;
    private String userName;
    private String userType;
    private String ipAddress;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime logDate;

}