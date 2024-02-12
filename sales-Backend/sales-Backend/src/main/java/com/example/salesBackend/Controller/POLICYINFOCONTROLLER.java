package com.example.salesBackend.Controller;

import com.example.salesBackend.Service.POLICYINFOSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/policyinfo")
public class POLICYINFOCONTROLLER {

    @Autowired
    private POLICYINFOSERVICE policyInfoService;

    @GetMapping("/policy-details")
    public List<Map<String, Object>> getPolicyDetailsWithSearchParams(
            @RequestParam(required = false) String POLICY_NO,
            @RequestParam(required = false) String NIC,
            @RequestParam(required = false) String NAME,
            @RequestParam(required = false) String CLIENT_NO,
            @RequestParam(required = false) String AGNTNUM

    ) {
        List<Object[]> result;

        if (POLICY_NO == null && NIC == null && NAME == null && CLIENT_NO == null && AGNTNUM == null) {
            System.out.println("No search parameters provided. Returning all details.");
            result = policyInfoService.getPolicyDetailsWithClientName();
        } else {
            System.out.println("Search parameters provided. Returning results based on search.");
            result = policyInfoService.getPolicyDetailsWithSearchParams(POLICY_NO, NIC, NAME, CLIENT_NO,AGNTNUM);
        }

        // Convert the result to pass with field names.
        List<Map<String, Object>> formattedResult = new ArrayList<>();
        IntStream.range(0, result.size()).forEach(i -> {
            Object[] item = result.get(i);

            Map<String, Object> formattedItem = new HashMap<>();
            formattedItem.put("POLICY_NO", item[0]);
            formattedItem.put("id",item[0]);
            formattedItem.put("NAME", item[1]);
            formattedItem.put("PREMIUM", item[2]);
            formattedItem.put("POLICY_STATUS", item[3]);
            formattedItem.put("AGNTNUM", item[4]);


            formattedResult.add(formattedItem);
        });
        return formattedResult;
    }

    @GetMapping("/policy-columns")
    public List<Map<String, Object>> getPolicyColumns(
            @RequestParam(required = true) String POLICY_NO
    ) {
        List<Object[]> result = policyInfoService.getPolicyColumns(POLICY_NO);

        // Convert the result to pass with field names.
        return result.stream()
                .map(item -> {
                    Map<String, Object> formattedItem = Map.of(
                            "POLICY_NO", item[0],
                            "PREMIUM", item[1],
                            "TOTAL_DUE", item[2],
                            "SUNDRY_BALANCE", item[3],
                            "PAIDUP_DATE", item[4],
                            "PLAN_NAME", item[5],
                            "PAYMENT_MODE", item[6],
                            "SUM_ASSURED", item[7],
                            "RISK_DATE", item[8],
                            "TERM", item[9]
                    );
                    return formattedItem;
                })
                .collect(Collectors.toList());
    }
}
