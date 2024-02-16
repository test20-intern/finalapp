package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.BENEFICIARYREQUEST;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Service.BENEFICIARYSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/beneficiary")
public class BENEFICIARYCONTROLLER {

    @Autowired
    private BENEFICIARYSERVICE beneficiaryService;

    @GetMapping("/beneficiary-details")
    public List<Map<String, Object>> getBeneficiaryDetailsByPolicyNo(
            @RequestParam(required = true) String policyNo
    ) {
        try {
            List<BENEFICIARYREQUEST> beneficiaryDetails = beneficiaryService.getBeneficiaryDetailsByPolicyNo(policyNo);

            // Convert the result to pass with field names and an incrementing "id".
            return beneficiaryDetails.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId()); // Incrementing "id"
                        formattedItem.put("RELATIONSHIP", item.getRELATIONSHIP());
                        formattedItem.put("NAME", item.getNAME());
                        formattedItem.put("DOB", item.getDOB());
                        formattedItem.put("PERCENTAGE", item.getPERCENTAGE());
                        return formattedItem;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // auto-incrementing id function
    private static long idCounter = 1;
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }
}
