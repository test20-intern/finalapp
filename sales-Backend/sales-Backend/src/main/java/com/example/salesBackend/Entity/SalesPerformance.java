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
    private long TgNOP;
    private long MCFP;
    private long TgMCFP;
    private long TP;
    private long TgTP;
    private long GTP;
    private long TgGTP;
    private long CUMNOP;
    private long TgCUMNOP;
    private long CUMMCFP;
    private long TgCUMMCFP;
    private long CUMTP;
    private long TgCUMTP;
    private long CUMGTP;
    private long TgCUMGTP;
    private Date LastUpdated;
}
