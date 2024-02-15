package com.example.salesBackend.Controller;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Entity.PG_POLICYLOAN;
import com.example.salesBackend.Service.CLIENTINFOSERVICE;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import com.example.salesBackend.Service.POLICYLOANSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/loans")
public class POLICYLOANCONTROLLER {
    @Autowired
    private POLICYLOANSERVICE policyloanservice;

    @Autowired
    private POLICYINFOSERVICE policyInfoService;

    @GetMapping("/loan-details")
    public List<Map<String, Object>> getLoanDetailsByPolicyNo(
            @RequestParam(required = true) String POLICY_NO
    ) {
        try {
            List<PG_POLICYLOAN> loanDetails = policyloanservice.getLoanDetailsByPolicyNo(POLICY_NO);

            // Convert the result to pass with field names and an incrementing "id".
            return loanDetails.stream()
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
        } catch (Exception e) {

            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // auto incrementing id function
    private static long idCounter = 1;
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }
}
