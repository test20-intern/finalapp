package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.LOANRECEIPTREQUEST;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.LOANRECEIPTSSERVICE;
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
@RequestMapping("/api/v1/loanReceipt")
public class LoanReceiptController {

    @Autowired
    private LOANRECEIPTSSERVICE loanReceiptsService;

    // Created an auto-incrementing counter to pass an id to the frontend.
    private static long idCounter = 1;

    @GetMapping("/loanReceipts/details")
    public ResponseEntity<AppResponse<List<Map<String, Object>>>> getLoanReceiptDetailsByPolicyNo(
            @RequestParam String POLICY_NO,
            @RequestParam String userType
    ) {
        try {
            List<LOANRECEIPTREQUEST> result = loanReceiptsService.getLoanReceiptDetailsByPolicyNo(POLICY_NO,userType);

            // Convert the result to pass with field names and an incrementing "id"
            List<Map<String, Object>> formattedResult = result.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId()); // Incrementing "id"
                        formattedItem.put("POLICY_NO", item.getPOLICY_NO());
                        formattedItem.put("LOAN_NO", item.getLOAN_NO());
                        formattedItem.put("RECEIPT_NO", item.getRECEIPT_NO());
                        formattedItem.put("RECEIPT_DATE", item.getRECEIPT_DATE());
                        formattedItem.put("AMOUNT", item.getAMOUNT());
                        return formattedItem;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(AppResponse.ok(formattedResult), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "LoanReceiptDetailsNotFound",
                    "Loan receipt details not found for policy number: " + POLICY_NO), HttpStatus.NOT_FOUND);
        } catch (BadRequestRuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetLoanReceiptDetailsOperationFailed",
                    "Error getting loan receipt details: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Incrementing "id" method
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }
}
