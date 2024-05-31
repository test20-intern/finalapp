package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemLogs {
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
