package com.example.salesBackend.Controller;


import com.example.salesBackend.Entity.PG_POLICYINFO;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoRepo;

    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoService;

    @GetMapping("/duePolicies")
//     API to get due policies. Due Policies= policies that are due
//     ( calculate using paidup_date column in policyInfo table) in upcoming days/ months.
    public ResponseEntity<AppResponse<List<PG_POLICYINFO>>> getDuePolicies(
            @RequestParam String agntnum,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inputDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam String userType
    ) {
        try {
            List<PG_POLICYINFO> duePolicies = pgPolicyInfoService.getDuePolicies(agntnum, inputDate, endDate,userType);

            if (duePolicies.isEmpty()) {
                return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "DuePoliciesNotFound",
                        "No due policies found for agent number: " + agntnum + ", input date: " + inputDate + ", and end date: " + endDate),
                        HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(AppResponse.ok(duePolicies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetDuePoliciesOperationFailed",
                    "Error getting due policies: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //     API to get Overdue policies. OverDue Policies= policies that were due
//     ( calculate using paidup_date column in policyInfo table) in past  days/ months.
    @GetMapping("/overduePolicies")
    public ResponseEntity<AppResponse<List<PG_POLICYINFO>>> getOverduePolicies(
            @RequestParam String agntnum,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inputDate,
            @RequestParam String userType
    ) throws ValueNotExistException {
        try {
            List<PG_POLICYINFO> overduePolicies = pgPolicyInfoService.getOverduePolicies(agntnum, inputDate, userType);

            if (overduePolicies.isEmpty()) {
                return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "OverduePoliciesNotFound",
                        "No overdue policies found for agent number: " + agntnum + " and input date: " + inputDate), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(AppResponse.ok(overduePolicies), HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e, "Error getting overdue policies");
        }
    }


    //     API to get Lapsed  policies. Lapsed Policies= policies that were due
//     ( calculate using paidup_date column in policyInfo table) before overdue period.
    @GetMapping("/lapsedPolicies")
    public ResponseEntity<AppResponse<List<PG_POLICYINFO>>> getLapsedPolicies(
            @RequestParam String agntnum,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inputDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam String userType
    ) {
        try {
            validateDates(inputDate, startDate);

            List<PG_POLICYINFO> lapsedPolicies = pgPolicyInfoService.getLapsedPolicies(agntnum, startDate, inputDate,userType);

            if (lapsedPolicies.isEmpty()) {
                return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "LapsedPoliciesNotFound",
                        "No lapsed policies found for agent number: " + agntnum + ", input date: " + inputDate + ", and start date: " + startDate),
                        HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(AppResponse.ok(lapsedPolicies), HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e, "Error getting lapsed policies");
        }
    }



 // the user should input a start date that is before date from the input date.
    private void validateDates(Date inputDate, Date startDate) throws BadRequestRuntimeException {
        if (startDate.after(inputDate)) {
            throw new BadRequestRuntimeException("Start date should be before or equal to the input date.");
        }
    }


    private ResponseEntity<AppResponse<List<PG_POLICYINFO>>> handleException(Exception e, String errorMessage) {
        if (e instanceof ValueNotExistException) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "DataNotFound", e.getMessage()), HttpStatus.NOT_FOUND);
        } else if (e instanceof BadRequestRuntimeException) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest", e.getMessage()), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "OperationFailed", errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
