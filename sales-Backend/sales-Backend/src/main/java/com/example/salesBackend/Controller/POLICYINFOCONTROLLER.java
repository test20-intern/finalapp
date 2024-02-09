package com.example.salesBackend.Controller;

import com.example.salesBackend.Service.POLICYINFOSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/policyinfo")
public class POLICYINFOCONTROLLER {

    @Autowired
    private POLICYINFOSERVICE policyInfoService;

    // In POLICYINFOCONTROLLER class
    @GetMapping("/policy-details")
    public List<Object[]> getPolicyDetailsWithSearchParams(
            @RequestParam(required = false) String policyNo,
            @RequestParam(required = false) String nic,
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) String clientId
    ) {
        if (policyNo == null && nic == null && clientName == null && clientId == null) {
            // If no search parameters provided, return all details
            List<Object[]> result = policyInfoService.getPolicyDetailsWithClientName();
            // Set temporaryId for each result item
            IntStream.range(0, result.size()).forEach(i -> {
                Object[] item = result.get(i);
                item[0] = (long) i + 1; // Set temporaryId
            });
            return result;
        } else {
            // If search parameters provided, return results based on search
            return policyInfoService.getPolicyDetailsWithSearchParams(policyNo, nic, clientName, clientId);
        }
    }}

