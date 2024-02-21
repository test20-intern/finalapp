package com.example.salesBackend.Controller;


import com.example.salesBackend.Entity.PG_POLICYINFO;
import com.example.salesBackend.Service.POLICYINFOSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoRepo;

    @GetMapping("/duePolicies")
    public List<PG_POLICYINFO> getDuePolicies(
            @RequestParam String agntnum,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inputDate
            // don't change the pattern "MM" to lowercase--> Not working.
    ) {
        return pgPolicyInfoRepo.getDuePolicies(agntnum, inputDate);
    }


}
