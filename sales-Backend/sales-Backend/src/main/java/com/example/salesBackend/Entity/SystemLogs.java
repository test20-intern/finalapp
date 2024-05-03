package com.example.salesBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private long empId;
    private String userName;
    private String userType;
    private String ipAddress;
    private LocalDateTime logDate;

}
