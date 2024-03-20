package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.RECEIPTSSERVICE;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/receipts")
public class ReceiptsController {

    @Autowired
    private RECEIPTSSERVICE pgReceiptsService;



    // auto-incrementing counter to pass an id to the frontend
    private static long idCounter = 1;

    @GetMapping("/details")
    public ResponseEntity<AppResponse<List<Map<String, Object>>>> getReceiptDetailsByPolicyNo(
            @RequestParam String POLICY_NO,
            @RequestParam String userType
    ) {
        try {
            List<RECEIPTREQUEST> result = pgReceiptsService.getReceiptDetailsByPolicyNo(POLICY_NO,userType);

            // Convert the result to pass with field names and an incrementing "id"
            List<Map<String, Object>> formattedResult = result.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId()); // Incrementing "id"
                        formattedItem.put("POLICY_NO", item.getPOLICY_NO());
                        formattedItem.put("RECEIPT_NO", item.getRECEIPT_NO());
                        formattedItem.put("RECEIPT_DATE", item.getRECEIPT_DATE());
                        formattedItem.put("AMOUNT", item.getAMOUNT());
                        return formattedItem;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(AppResponse.ok(formattedResult), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "ReceiptDetailsNotFound",
                    "Receipt details not found for policy number: " + POLICY_NO), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetReceiptDetailsOperationFailed",
                    "Error getting receipt details: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Incrementing "id" method
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }


    // API for get receipt details for a given date range.
    @GetMapping("/agentReceipts")
    public ResponseEntity<AppResponse<List<Map<String, Object>>>> getAgentReceipts(
            @RequestParam String agntnum,
            @RequestParam(required = false) String policyNo,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam String userType
    ) {
        try {
            List<Map<String, Object>> agentReceipts = pgReceiptsService.getAgentReceiptsMapped(agntnum, policyNo, startDate, endDate,userType);

            if (agentReceipts.isEmpty()) {
                return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "AgentReceiptsNotFound",
                        "No agent receipts found for agent number: " + agntnum + ", start date: " + startDate + ", end date: " + endDate +
                                (policyNo != null ? ", and policy number: " + policyNo : "")), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(AppResponse.ok(agentReceipts), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetAgentReceiptsOperationFailed",
                    "Error getting agent receipts: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}
