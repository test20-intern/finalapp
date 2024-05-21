package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Response.SalesPerformanceDTO;

import com.example.salesBackend.Repo.SalesPerformanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
            dto.setTotalNOP(convertToBigDecimal(result[1]));
            dto.setTotalTgNOP(convertToBigDecimal(result[2]));
            dto.setTotalTP(convertToBigDecimal(result[3]));
            dto.setTotalTgTP(convertToBigDecimal(result[4]));
            dto.setTotalMCFP(convertToBigDecimal(result[5]));
            dto.setTotalTgMCFP(convertToBigDecimal(result[6]));
            dto.setTotalGTP(convertToBigDecimal(result[7]));
            dto.setTotalTgGTP(convertToBigDecimal(result[8]));
            dto.setTotalCUMNOP(convertToBigDecimal(result[9]));
            dto.setTotalTgCUMNOP(convertToBigDecimal(result[10]));
            dto.setTotalCUMMCFP(convertToBigDecimal(result[11]));
            dto.setTotalTgCUMMCFP(convertToBigDecimal(result[12]));
            dto.setTotalCUMTP(convertToBigDecimal(result[13]));
            dto.setTotalTgCUMTP(convertToBigDecimal(result[14]));
            dto.setTotalCUMGTP(convertToBigDecimal(result[15]));
            dto.setTotalTgCUMGTP(convertToBigDecimal(result[16]));
            return dto;
        }
        return null;
    }
    private BigDecimal convertToBigDecimal(Object value) {
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof Integer) {
            return new BigDecimal((Integer) value);
        } else if (value instanceof Long) {
            return new BigDecimal((Long) value);
        } else if (value instanceof String) {
            return new BigDecimal((String) value);
        }
        throw new IllegalArgumentException("Unsupported type for conversion to BigDecimal: " + value.getClass().getName());
    }
}
