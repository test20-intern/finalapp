package com.example.salesBackend.Controller;

import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/policyinfo")
public class PolicyInfoController {

    @Autowired
    private POLICYINFOSERVICE policyInfoService;

    @GetMapping("/policy-details")
    public ResponseEntity<AppResponse<List<Map<String, Object>>>> getPolicyDetailsWithSearchParams(
            @RequestParam(required = false) String POLICY_NO,
            @RequestParam(required = false) String NIC,
            @RequestParam(required = false) String NAME,
            @RequestParam(required = false) String CLIENT_NO,
            @RequestParam(required = false) String AGNTNUM
    ) {
        try {
            List<Object[]> result;

            if (POLICY_NO == null && NIC == null && NAME == null && CLIENT_NO == null && AGNTNUM == null) {
                System.out.println("No search parameters provided. Returning all details.");
                result = policyInfoService.getPolicyDetailsWithClientName();
            } else {
                System.out.println("Search parameters provided. Returning results based on search.");
                result = policyInfoService.getPolicyDetailsWithSearchParams(POLICY_NO, NIC, NAME, CLIENT_NO, AGNTNUM);

                // If no data is found for the given search parameters, throw ValueNotExistException
                if (result.isEmpty()) {
                    throw new ValueNotExistException("No data found for the provided search parameters");
                }
            }

            // Convert the result to pass with field names and an incrementing "id".
            List<Map<String, Object>> formattedResult = result.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId()); // Incrementing "id"
                        formattedItem.put("POLICY_NO", item[0]);
                        formattedItem.put("NAME", item[1]);
                        formattedItem.put("PREMIUM", item[2]);
                        formattedItem.put("POLICY_STATUS", item[3]);
                        formattedItem.put("AGNTNUM", item[4]);
                        return formattedItem;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(AppResponse.ok(formattedResult), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "PolicyDetailsNotFound",
                    "Policy details not found for the provided search parameters"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetPolicyDetailsOperationFailed",
                    "Error getting policy details: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/policy-columns")
    public ResponseEntity<AppResponse<List<Map<String, Object>>>> getPolicyColumns(
            @RequestParam(required = true) String POLICY_NO
    ) {
        try {
            List<Object[]> result = policyInfoService.getPolicyColumns(POLICY_NO);

            // If no data is found for the given POLICY_NO, throw ValueNotExistException
            if (result == null || result.isEmpty()) {
                throw new ValueNotExistException("No policy columns found for the provided POLICY_NO: " + POLICY_NO);
            }

            // Convert the result to pass with field names and an incrementing "id".
            List<Map<String, Object>> formattedResult = result.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId());
                        formattedItem.put("POLICY_NO", item[0]);
                        formattedItem.put("PREMIUM", item[1]);
                        formattedItem.put("TOTAL_DUE", item[2]);
                        formattedItem.put("SUNDRY_BALANCE", item[3]);
                        formattedItem.put("PAIDUP_DATE", item[4]);
                        formattedItem.put("PLAN_NAME", item[5]);
                        formattedItem.put("PAYMENT_MODE", item[6]);
                        formattedItem.put("SUM_ASSURED", item[7]);
                        formattedItem.put("RISK_DATE", item[8]);
                        formattedItem.put("TERM", item[9]);
                        return formattedItem;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(AppResponse.ok(formattedResult), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "PolicyColumnsNotFound",
                    "Policy columns not found for the provided POLICY_NO: " + POLICY_NO), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetPolicyColumnsOperationFailed",
                    "Error getting policy columns: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/policy-benefit-details")
    public ResponseEntity<AppResponse<List<Map<String, Object>>>> getPolicyBenefitDetailsByPolicyNo(
            @RequestParam(required = true) String POLICY_NO
    ) {
        try {
            List<Object[]> result = policyInfoService.getPolicyBenefitDetailsByPolicyNo(POLICY_NO);

            // If no data is found for the given POLICY_NO, throw ValueNotExistException
            if (result == null || result.isEmpty()) {
                throw new ValueNotExistException("No policy benefit details found for the provided POLICY_NO: " + POLICY_NO);
            }

            // Convert the result to pass with field names and an incrementing "id".
            List<Map<String, Object>> formattedResult = result.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId());
                        formattedItem.put("BENEFIT_CODE", item[0]);
                        formattedItem.put("SUM_ASSURED", item[1]);
                        formattedItem.put("TERM", item[2]);
                        formattedItem.put("PREMIUM", item[3]);
                        return formattedItem;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(AppResponse.ok(formattedResult), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "PolicyBenefitDetailsNotFound",
                    "Policy benefit details not found for the provided POLICY_NO: " + POLICY_NO), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetPolicyBenefitDetailsOperationFailed",
                    "Error getting policy benefit details: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Created an auto-incrementing counter to pass an id to the frontend.
    private static long idCounter = 1;
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }
}