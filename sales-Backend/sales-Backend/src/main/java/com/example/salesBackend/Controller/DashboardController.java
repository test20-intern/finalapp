// DashboardController.java

package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.DashboardCounts;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoService;

    /* API for graph values for policy details */
    @GetMapping("/policyCounts")
    public ResponseEntity<AppResponse<DashboardCounts>> getPolicyCounts(
            @RequestParam String agntnum,
            @RequestParam String userType
    ) {
        try {
            DashboardCounts counts = pgPolicyInfoService.getPolicyCounts(agntnum,userType);
            return new ResponseEntity<>(AppResponse.ok(counts), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "DataNotFound", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetPolicyCountsOperationFailed",
                    "Error getting policy counts: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPlanTypes")
    public ResponseEntity<AppResponse<Map<String, Long>>> getPlanTypes(
            @RequestParam String agntnum
    ) {
        try {
            Map<String, Long> planTypes = pgPolicyInfoService.getPlanTypes(agntnum);
            if (planTypes.isEmpty()) {
                throw new ValueNotExistException("No plan types found for agent number: " + agntnum);
            }
            return new ResponseEntity<>(AppResponse.ok(planTypes), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "PlanTypesNotFound", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetPlanTypesOperationFailed",
                    "Error getting plan types: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



