package com.example.salesBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="SalesPerformance")
public class SalesPerformance {


    @Id
    private String SOCode;
    private long NOP;
    private long MCFP;
    private long TP;
    private long GTP;
    private long CUMNOP;
    private long CUMMCFP;
    private long CUMTP;
    private long CUMGTP;
    private Date LastUpdated;
}
