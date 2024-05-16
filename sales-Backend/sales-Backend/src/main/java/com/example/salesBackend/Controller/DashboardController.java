
package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.DashboardCounts;
import com.example.salesBackend.Dto.Response.SalesPerformanceDTO;
import com.example.salesBackend.Dto.Response.TotalAmountsForEachDay;

import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import com.example.salesBackend.Service.RECEIPTSSERVICE;
import com.example.salesBackend.Service.SalesPerformanceService;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoService;

    @Autowired
    private RECEIPTSSERVICE receiptDetailsService;

    @Autowired
    private SalesPerformanceService salesPerformanceService;



    /* API for graph values for policy details */
    @GetMapping("/policyCounts")
    public ResponseEntity<AppResponse<DashboardCounts>> getPolicyCounts(
            @RequestParam String agntnum,
            @RequestParam String userType,
            @RequestParam("groupCode") String groupCode,
            @RequestParam("branchCode") String branchCode,
            @RequestParam("unitCode") String unitCode
    ) {
        try {
            DashboardCounts counts = pgPolicyInfoService.getPolicyCounts(groupCode,branchCode,unitCode,agntnum,userType);
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

    @GetMapping("/totalAmount")
    public ResponseEntity<List<TotalAmountsForEachDay>> getTotalAmount(
            @RequestParam("groupCode") String groupCode,
            @RequestParam("branchCode") String branchCode,
            @RequestParam("unitCode") String unitCode,
            @RequestParam("agntnum") String agntnum,
            @RequestParam("userType") String userType,
            @RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inputDate
    ) {
        List<TotalAmountsForEachDay> totalAmounts = receiptDetailsService.getTotalAmount(groupCode, branchCode, unitCode, agntnum, userType, inputDate);
        return ResponseEntity.ok(totalAmounts);
    }




    @GetMapping("/performance")
    public SalesPerformanceDTO getSalesPerformance(
            @RequestParam String groupCode,
            @RequestParam String branchCode,
            @RequestParam String unitCode,
            @RequestParam String agntnum,
            @RequestParam String userType) {
        return salesPerformanceService.getSalesPerformance(groupCode, branchCode, unitCode, agntnum, userType);
    }







}