package com.example.salesBackend.Service;
import com.example.salesBackend.Dto.Request.HierarchyRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
public class HierarchyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getSOCode(HierarchyRequest request) {
        String query = "EXEC SalesApp_Select_Agents ?, ?, ?, ?";
        List<String> soCodes = jdbcTemplate.queryForList(query, new Object[]{request.getGroupCode(), request.getBranchCode(), request.getUnitCode(), request.getUserType()}, String.class);
        return soCodes;
    }
}

