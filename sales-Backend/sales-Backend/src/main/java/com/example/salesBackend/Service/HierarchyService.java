package com.example.salesBackend.Service;
import com.example.salesBackend.Dto.Request.HierarchyRequest;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
public class HierarchyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getSOCode(HierarchyRequest request) throws BadRequestRuntimeException, ValueNotExistException {
        // Check for invalid parameters
        if (request.getGroupCode() == null || request.getBranchCode() == null || request.getUnitCode() == null || request.getUserType() == null) {
            throw new BadRequestRuntimeException("One or more parameters are null");
        }

        String query = "EXEC SalesApp_Select_Agents ?, ?, ?, ?";
        List<String> soCodes = jdbcTemplate.queryForList(query, new Object[]{request.getGroupCode(), request.getBranchCode(), request.getUnitCode(), request.getUserType()}, String.class);

        // Check if the result is empty
        if (soCodes.isEmpty()) {
            throw new ValueNotExistException("SO code not found for the provided hierarchy");
        }

        return soCodes;
    }

    public List<String> getUnitCode(HierarchyRequest request) throws BadRequestRuntimeException, ValueNotExistException {
        // Check for invalid parameters
        if (request.getGroupCode() == null || request.getBranchCode() == null || request.getUnitCode() == null || request.getUserType() == null) {
            throw new BadRequestRuntimeException("One or more parameters are null");
        }
        String query = "EXEC SalesApp_Select_UnitCode ?, ?, ?, ?";
        List<String> unitCodes = jdbcTemplate.queryForList(query, new Object[]{request.getGroupCode(), request.getBranchCode(), request.getUnitCode(), request.getUserType()}, String.class);

        // Check if the result is empty
        if (unitCodes.isEmpty()) {
            throw new ValueNotExistException("Unit code not found for the provided hierarchy");
        }
        return unitCodes;
    }

    public List<String> getBranchCode(HierarchyRequest request) throws BadRequestRuntimeException, ValueNotExistException {
        // Check for invalid parameters
        if (request.getGroupCode() == null || request.getBranchCode() == null || request.getUnitCode() == null || request.getUserType() == null) {
            throw new BadRequestRuntimeException("One or more parameters are null");
        }
        String query = "EXEC SalesApp_Select_BranchCode ?, ?, ?, ?";
        List<String> branchCodes = jdbcTemplate.queryForList(query, new Object[]{request.getGroupCode(), request.getBranchCode(), request.getUnitCode(), request.getUserType()}, String.class);

        // Check if the result is empty
        if (branchCodes.isEmpty()) {
            throw new ValueNotExistException("Branch code not found for the provided hierarchy");
        }
        return branchCodes;
    }




}

