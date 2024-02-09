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
            @RequestParam(required = false) String clientId
    ) {
        List<Object[]> result;

        if (policyNo == null && nic == null && clientName == null && clientId == null) {
            // If no search parameters provided, return all details
            result = policyInfoService.getPolicyDetailsWithClientName();
        } else {
            // If search parameters provided, return results based on search
            result = policyInfoService.getPolicyDetailsWithSearchParams(policyNo, nic, clientName, clientId);
        }

        // Convert the result to the desired format
        List<Map<String, Object>> formattedResult = new ArrayList<>();
        IntStream.range(0, result.size()).forEach(i -> {
            Object[] item = result.get(i);
            Map<String, Object> formattedItem = new HashMap<>();
            formattedItem.put("id", (long) i + 1);
            formattedItem.put("name", item[1]);
            formattedItem.put("premium", item[2]);
            formattedItem.put("status", item[3]);
            formattedResult.add(formattedItem);
        });
        return formattedResult;
    }
}
