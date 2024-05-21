package com.example.salesBackend.Dto.Response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@Data
@Getter
@Setter
public class SalesPerformanceDTO {
    private Date lastUpdate;
    private long totalNOP;
    private long totalTgNOP;
    private long totalMCFP;
    private long totalTgMCFP;
    private long totalTP;
    private long totalTgTP;
    private long totalGTP;
    private long totalTgGTP;
    private long totalCUMNOP;
    private long totalTgCUMNOP;
    private long totalCUMMCFP;
    private long totalTgCUMMCFP;
    private long  totalCUMTP;
    private long  totalTgCUMTP;
    private long totalCUMGTP;
    private long totalTgCUMGTP;


}
