package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Response.SalesPerformanceDTO;

import com.example.salesBackend.Repo.SalesPerformanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesPerformanceService {

    @Autowired
    private SalesPerformanceRepo salesPerformanceRepo;



    public SalesPerformanceDTO getSalesPerformance(String groupCode, String branchCode, String unitCode, String agntnum, String userType) {
        List<Object> results = salesPerformanceRepo.getTotalPerformance(groupCode, branchCode, unitCode, agntnum, userType);

        if (!results.isEmpty()) {
            Object[] result = (Object[]) results.get(0);
            SalesPerformanceDTO dto = new SalesPerformanceDTO();
            dto.setLastUpdate((Date) result[0]);
            dto.setTotalNOP(((Number) result[1]).longValue());
            dto.setTotalTgNOP(((Number) result[2]).longValue());
            dto.setTotalTP(((Number) result[3]).longValue());
            dto.setTotalTgTP(((Number) result[4]).longValue());
            dto.setTotalMCFP(((Number) result[5]).longValue());
            dto.setTotalTgMCFP(((Number) result[6]).longValue());
            dto.setTotalGTP(((Number) result[7]).longValue());
            dto.setTotalTgGTP(((Number) result[8]).longValue());

            dto.setTotalCUMNOP(((Number) result[9]).longValue());
            dto.setTotalTgCUMNOP(((Number) result[10]).longValue());
            dto.setTotalCUMMCFP(((Number) result[11]).longValue());
            dto.setTotalTgCUMMCFP(((Number) result[12]).longValue());
            dto.setTotalCUMTP(((Number) result[13]).longValue());
            dto.setTotalTgCUMTP(((Number) result[14]).longValue());
            dto.setTotalCUMGTP(((Number) result[15]).longValue());
            dto.setTotalTgCUMGTP(((Number) result[16]).longValue());
            return dto;
        }
        return null;
    }
}
