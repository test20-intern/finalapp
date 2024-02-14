package com.example.salesBackend.Controller;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Service.CLIENTINFOSERVICE;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<PG_CLIENTINFO> clientDetails = clientInfoService.getClientDetailsByPolicyNo(POLICY_NO);

        // Convert the result to pass with field names.
        return clientDetails.stream()
                .map(item -> {
                    Map<String, Object> formattedItem = Map.of(
                            "CLIENT_NO", item.getCLIENT_NO(),
                            "NIC", item.getNIC(),
                            "FULL_NAME", item.getFULL_NAME(),
                            "ADD_1", item.getADD_1(),
                            "ADD_2", item.getADD_2(),
                            "ADD_CITY", item.getADD_CITY(),
                            "PCODE", item.getPCODE(),
                            "TEL_1", item.getTEL_1(),
                            "TEL_2", item.getTEL_2()
                    );
                    return formattedItem;
                })
                .collect(Collectors.toList());
    }
}
