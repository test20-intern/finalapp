package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Service.RECEIPTSSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/receipts")
public class RECEIPTSCONTROLLER {

    @Autowired
    private RECEIPTSSERVICE pgReceiptsService;

    @GetMapping("/details")
    public List<RECEIPTREQUEST> getReceiptDetailsByPolicyNo(
            @RequestParam String POLICY_NO
    ) {
        return pgReceiptsService.getReceiptDetailsByPolicyNo(POLICY_NO);
    }
}
