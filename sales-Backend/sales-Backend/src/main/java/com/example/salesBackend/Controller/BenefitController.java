package com.example.salesBackend.Controller;


import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_BENEFITREPO;
import com.example.salesBackend.Service.BENEFITSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/benefit")
public class BenefitController {
    private final PG_BENEFITREPO benefitRepository;
    private final BENEFITSERVICE benefitService;
    @Autowired
    public BenefitController(PG_BENEFITREPO benefitRepository, BENEFITSERVICE benefitService) {
        this.benefitRepository = benefitRepository;
        this.benefitService = benefitService;
    }


// API for get all the benefits that get by one policy number ( life number wise)
    @GetMapping("/details")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getBenefitDetailsByPolicyNo(
            @RequestParam String policyNo,
            @RequestParam String userType
            ) {
        try {
            List<Object[]> results = benefitService.getBenefitDetailsByPolicyNo(policyNo,userType);

            Map<String, List<Map<String, Object>>> categorizedDetails = new HashMap<>();

            long idCounter = 1; // Initialize auto-incrementing ID counter

            for (Object[] row : results) {
                String lifeNo = String.valueOf(row[0]);

                Map<String, Object> detail = new HashMap<>();
                detail.put("id", idCounter++); // Auto-incrementing ID
                detail.put("benefit_code", row[1]);
                detail.put("description", row[2]);
                detail.put("cover_amount", row[3]);

                categorizedDetails.computeIfAbsent(lifeNo, k -> new ArrayList<>()).add(detail);
            }

            return new ResponseEntity<>(categorizedDetails, HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (BadRequestRuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
