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
    public List<BirthdaysResponse> getBeneficiaryBirthdays(String agntnum, Date startDate, Date endDate, String userType, String groupCode, String branchCode, String unitCode) throws ValueNotExistException {
        List<Object[]> result = beneficiaryRepo.findBirthdaysByAgentNumber(agntnum, startDate, endDate, userType, groupCode, branchCode, unitCode);
        List<BirthdaysResponse> birthdaysResponseList = new ArrayList<>();
        HashSet<String> uniqueSet = new HashSet<>();

        for (Object[] row : result) {
            String customerId = (String) row[0];
            Date dob = (Date) row[1];
            String name = (String) row[2];
            String relationship = (String) row[3];
            String clientNo = (String) row[4];
            String addCity = (String) row[5];
            String tel1 = (String) row[6];
            String tel2 = (String) row[7];
            String agentNumber = (String) row[8];  // Add this line

            String uniqueKey = customerId + dob.toString() + name;

            if (!uniqueSet.contains(uniqueKey)) {
                PG_BENEFICIARY beneficiary = new PG_BENEFICIARY();
                PG_CLIENTINFO clientInfo = new PG_CLIENTINFO();

                BeneficiaryId beneficiaryId = new BeneficiaryId();
                beneficiaryId.setCUSTOMERID(customerId);
                beneficiaryId.setRELATIONSHIP(relationship);

                beneficiary.setBID(beneficiaryId);
                beneficiary.setNAME(name);
                beneficiary.setDOB(dob);

                clientInfo.setCLIENT_NO(clientNo);
                clientInfo.setADD_CITY(addCity);
                clientInfo.setTEL_1(tel1);
                clientInfo.setTEL_2(tel2);

                BirthdaysResponse birthdaysResponse = new BirthdaysResponse(beneficiary, clientInfo);
                birthdaysResponseList.add(birthdaysResponse);

                uniqueSet.add(uniqueKey);
            }
        }

        if (birthdaysResponseList.isEmpty()) {
            throw new ValueNotExistException("No beneficiaries found with birthdays between the given date range");
        }

        return birthdaysResponseList;
    }
}



