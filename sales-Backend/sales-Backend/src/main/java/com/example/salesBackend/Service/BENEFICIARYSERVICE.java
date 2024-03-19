package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.BENEFICIARYREQUEST;
import com.example.salesBackend.Dto.Response.BirthdaysResponse;
import com.example.salesBackend.Entity.BeneficiaryId;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_BENEFICIARYREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BENEFICIARYSERVICE {

    @Autowired
    private PG_BENEFICIARYREPO beneficiaryRepo;

    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoService;

    public List<BENEFICIARYREQUEST> getBeneficiaryDetailsByPolicyNo(String policyNo,String userType) throws ValueNotExistException, BadRequestRuntimeException {
        if (policyNo == null || policyNo.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<PG_BENEFICIARY> beneficiaries = beneficiaryRepo.findByPolicyNo(policyNo,userType);
        if (beneficiaries.isEmpty()) {
            throw new ValueNotExistException("Beneficiary details not found for policy number: " + policyNo);
        }

        Set<String> uniqueBeneficiaryKeys = new HashSet<>();
        return beneficiaries.stream()
                .filter(beneficiary -> {
                    String key = getKey(beneficiary);
                    boolean isDuplicate = !uniqueBeneficiaryKeys.add(key);
                    return !isDuplicate;
                })
                .map(this::mapEntityToRequest)
                .collect(Collectors.toList());
    }

    private String getKey(PG_BENEFICIARY beneficiary) {
        // Combine DOB, RELATIONSHIP, and NAME to form a unique key
        return beneficiary.getDOB().toString() + beneficiary.getBID().getRELATIONSHIP() + beneficiary.getNAME();
    }

    private BENEFICIARYREQUEST mapEntityToRequest(PG_BENEFICIARY beneficiary) {
        BENEFICIARYREQUEST request = new BENEFICIARYREQUEST();
        request.setRELATIONSHIP(beneficiary.getBID().getRELATIONSHIP());
        request.setNAME(beneficiary.getNAME());
        request.setDOB(beneficiary.getDOB());
        request.setPERCENTAGE(beneficiary.getPERCENTAGE());
        return request;
    }


    // BeneficiaryService.java
    public List<BirthdaysResponse> getBeneficiaryBirthdays(String agntnum, Date startDate, Date endDate,String userType) throws ValueNotExistException {
        List<Object[]> result = beneficiaryRepo.findBirthdaysByAgentNumber(agntnum, startDate, endDate,userType);
        List<BirthdaysResponse> birthdaysResponseList = new ArrayList<>();
        HashSet<String> uniqueSet = new HashSet<>();

        for (Object[] row : result) {
            // Assuming the order of columns in the result matches the constructor parameters of BirthdaysResponse
            String customerId = (String) row[0];
            Date dob = (Date) row[1];
            String name = (String) row[2];

            // Create a unique key using the combination of CLIENT_NO, DOB, and NAME
            String uniqueKey = customerId + dob.toString() + name;

            // Check if the unique key already exists in the HashSet
            if (!uniqueSet.contains(uniqueKey)) {
                PG_BENEFICIARY beneficiary = new PG_BENEFICIARY();
                PG_CLIENTINFO clientInfo = new PG_CLIENTINFO();

                // Assuming the order of columns in the result matches the constructor parameters of BirthdaysResponse
                BeneficiaryId beneficiaryId = new BeneficiaryId();
                beneficiaryId.setSOCODE((String) row[4]);
                beneficiaryId.setCUSTOMERID(customerId);
                beneficiaryId.setRELATIONSHIP((String) row[3]);

                beneficiary.setBID(beneficiaryId);
                beneficiary.setNAME(name);
                beneficiary.setDOB(dob);


                clientInfo.setCLIENT_NO((String) row[5]);
                clientInfo.setADD_CITY((String) row[6]);
                clientInfo.setTEL_1((String) row[7]);
                clientInfo.setTEL_2((String) row[8]);

                BirthdaysResponse birthdaysResponse = new BirthdaysResponse(beneficiary, clientInfo);
                birthdaysResponseList.add(birthdaysResponse);

                // Add the unique key to the HashSet to mark it as processed
                uniqueSet.add(uniqueKey);
            }
        }

        if (birthdaysResponseList.isEmpty()) {
            throw new ValueNotExistException("No beneficiaries found with birthdays between the given date range");
        }

        return birthdaysResponseList;
    }
}



