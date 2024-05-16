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
    private Long totalNOP;
    private Long totalMCFP;
    private Long totalGTP;
    private Long totalCUMNOP;
    private Long totalCUMMCFP;
    private Long totalCUMGTP;


}
