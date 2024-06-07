package com.example.salesBackend.Dto.Response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


@NoArgsConstructor
@Data
@Getter
@Setter
public class SalesPerformanceDTO {
    private Date lastUpdate;
    private BigDecimal totalNOP;
    private BigDecimal totalTgNOP;
    private BigDecimal totalMCFP;
    private BigDecimal totalTgMCFP;
    private BigDecimal totalTP;
    private BigDecimal totalTgTP;
    private BigDecimal totalGTP;
    private BigDecimal totalTgGTP;
    private BigDecimal totalCUMNOP;
    private BigDecimal totalTgCUMNOP;
    private BigDecimal totalCUMMCFP;
    private BigDecimal totalTgCUMMCFP;
    private BigDecimal  totalCUMTP;
    private BigDecimal  totalTgCUMTP;
    private BigDecimal totalCUMGTP;
    private BigDecimal totalTgCUMGTP;


}
