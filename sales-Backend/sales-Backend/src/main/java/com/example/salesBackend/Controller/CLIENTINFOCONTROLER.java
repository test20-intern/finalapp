package com.example.salesBackend.Controller;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Service.CLIENTINFOSERVICE;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/clientinfo")
public class CLIENTINFOCONTROLER {

    @Autowired
    private CLIENTINFOSERVICE clientInfoService;

    @Autowired
    private POLICYINFOSERVICE policyInfoService;

    @GetMapping("/client-details")
    public List<Map<String, Object>> getClientDetailsByPolicyNo(
            @RequestParam(required = true) String POLICY_NO
    ) {
        try {
            List<PG_CLIENTINFO> clientDetails = clientInfoService.getClientDetailsByPolicyNo(POLICY_NO);

            // Convert the result to pass with field names and an incrementing "id".
            return clientDetails.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId()); // Incrementing "id"
                        formattedItem.put("CLIENT_NO", item.getCLIENT_NO());
                        formattedItem.put("NIC", item.getNIC());
                        formattedItem.put("FULL_NAME", item.getFULL_NAME());
                        formattedItem.put("ADD_1", item.getADD_1());
                        formattedItem.put("ADD_2", item.getADD_2());
                        formattedItem.put("ADD_CITY", item.getADD_CITY());
                        formattedItem.put("PCODE", item.getPCODE());
                        formattedItem.put("TEL_1", item.getTEL_1());
                        formattedItem.put("TEL_2", item.getTEL_2());
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

