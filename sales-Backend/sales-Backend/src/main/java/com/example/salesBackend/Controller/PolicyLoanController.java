package com.example.salesBackend.Controller;

import com.example.salesBackend.Entity.PG_POLICYLOAN;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.POLICYLOANSERVICE;
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
@RequestMapping("/api/v1/loans")
public class PolicyLoanController {

    @Autowired
    private POLICYLOANSERVICE policyloanservice;

    // auto incrementing id function
    private static long idCounter = 1;

    @GetMapping("/loan-details")
    public ResponseEntity<AppResponse<List<Map<String, Object>>>> getLoanDetailsByPolicyNo(
            @RequestParam(required = true) String POLICY_NO,
            @RequestParam(required = true) String userType
    ) {
        try {
            List<PG_POLICYLOAN> loanDetails = policyloanservice.getLoanDetailsByPolicyNo(POLICY_NO,userType);

            // Convert the result to pass with field names and an incrementing "id".
            List<Map<String, Object>> formattedResult = loanDetails.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId()); // Incrementing "id"
                        formattedItem.put("POLICY_NO", item.getPOLICY_NO());
                        formattedItem.put("LAST_CAPITALIZED_DATE", item.getLAST_CAPITALIZED_DATE());
                        formattedItem.put("LOAN_BALANCE", item.getLOAN_BALANCE());
                        formattedItem.put("LOAN_DATE", item.getLOAN_DATE());
                        formattedItem.put("LOAN_NO", item.getLOAN_NO());

                        return formattedItem;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(AppResponse.ok(formattedResult), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "LoanDetailsNotFound",
                    "Loan details not found for policy number: " + POLICY_NO), HttpStatus.NOT_FOUND);
        } catch (BadRequestRuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetLoanDetailsOperationFailed",
                    "Error getting loan details: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Incrementing "id" method
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }
}
