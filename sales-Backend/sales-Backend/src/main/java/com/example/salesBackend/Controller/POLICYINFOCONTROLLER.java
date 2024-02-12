package com.example.salesBackend.Controller;

import com.example.salesBackend.Service.POLICYINFOSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/policyinfo")
public class POLICYINFOCONTROLLER {

    @Autowired
    private POLICYINFOSERVICE policyInfoService;

    @GetMapping("/policy-details")
    public List<Map<String, Object>> getPolicyDetailsWithSearchParams(
            @RequestParam(required = false) String policyNo,
            @RequestParam(required = false) String nic,
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) String clientId,
            @RequestParam(required = false) String agntnum

    ) {
        List<Object[]> result;

        if (policyNo == null && nic == null && clientName == null && clientId == null && agntnum == null) {
            System.out.println("No search parameters provided. Returning all details.");
            result = policyInfoService.getPolicyDetailsWithClientName();
        } else {
            System.out.println("Search parameters provided. Returning results based on search.");
            result = policyInfoService.getPolicyDetailsWithSearchParams(policyNo, nic, clientName, clientId,agntnum);
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
}
