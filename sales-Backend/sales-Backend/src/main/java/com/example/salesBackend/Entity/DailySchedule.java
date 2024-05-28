package com.example.salesBackend.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "DailySchedule")
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long RECORD_ID;
    private String SO_CODE;
    private Date START;
    private Date END;
    private String TITLE;
    private String STATUS;

}
