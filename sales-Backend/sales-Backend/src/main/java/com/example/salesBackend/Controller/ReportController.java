package com.example.salesBackend.Controller;


import com.example.salesBackend.Entity.PG_POLICYINFO;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoRepo;

    @GetMapping("/duePolicies")
    public ResponseEntity<AppResponse<List<PG_POLICYINFO>>> getDuePolicies(
            @RequestParam String agntnum,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inputDate
    ) {
        try {
            List<PG_POLICYINFO> duePolicies = pgPolicyInfoRepo.getDuePolicies(agntnum, inputDate);

            if (duePolicies.isEmpty()) {
                return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "DuePoliciesNotFound",
                        "No due policies found for agent number: " + agntnum + " and input date: " + inputDate), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(AppResponse.ok(duePolicies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetDuePoliciesOperationFailed",
                    "Error getting due policies: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
