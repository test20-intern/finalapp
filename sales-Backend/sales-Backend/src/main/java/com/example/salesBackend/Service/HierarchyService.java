package com.example.salesBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HierarchyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getSOCode(com.example.salesBackend.Dto.Request.HierarchyRequest request) {
        String query = "EXEC SalesApp_Select_Agents ?, ?, ?, ?";
        List<String> soCodes = jdbcTemplate.queryForList(query, new Object[]{request.getGroupCode(), request.getBranchCode(), request.getUnitCode(), request.getUserType()}, String.class);
        return soCodes;
    }
}
