package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DailySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long RECORD_ID;
    private String agntnum;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String TITLE;
    private String STATUS;
    private Boolean AllDay;
}
