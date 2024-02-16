
package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.LOANRECEIPTREQUEST;

import com.example.salesBackend.Entity.PG_LOANRECEIPTS;
import com.example.salesBackend.Service.LOANRECEIPTSSERVICE;
import com.example.salesBackend.Service.RECEIPTSSERVICE;
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
public class LOANRECEIPTCONTROLLER {

    @Autowired
    private LOANRECEIPTSSERVICE loanReceiptsService;




    // Created an auto-incrementing counter to pass an id to the frontend.
    private static long idCounter = 1;

    @GetMapping("/details")
    public ResponseEntity<List<Map<String, Object>>> getloanReceiptDetailsByPolicyNo(
            @RequestParam String POLICY_NO
    ) {
        try {
            List<LOANRECEIPTREQUEST> result = loanReceiptsService.getloanReceiptDetailsByPolicyNo(POLICY_NO);

            // Convert the result to pass with field names and an incrementing "id".
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

            return new ResponseEntity<>(formattedResult, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Incrementing "id" method
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }




}
