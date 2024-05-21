package com.example.salesBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="SalesPerformance")
public class SalesPerformance {


    @Id
    private String SOCode;
    private BigDecimal NOP;
    private BigDecimal TgNOP;
    private BigDecimal MCFP;
    private BigDecimal TgMCFP;
    private BigDecimal TP;
    private BigDecimal TgTP;
    private BigDecimal GTP;
    private BigDecimal TgGTP;
    private BigDecimal CUMNOP;
    private BigDecimal TgCUMNOP;
    private BigDecimal CUMMCFP;
    private BigDecimal TgCUMMCFP;
    private BigDecimal CUMTP;
    private BigDecimal TgCUMTP;
    private BigDecimal CUMGTP;
    private BigDecimal TgCUMGTP;
    private Date LastUpdated;
}
