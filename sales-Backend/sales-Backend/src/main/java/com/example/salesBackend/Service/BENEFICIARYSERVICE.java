package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.BENEFICIARYREQUEST;
import com.example.salesBackend.Dto.Response.BirthdaysResponse;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_BENEFICIARYREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BENEFICIARYSERVICE {

    @Autowired
    private PG_BENEFICIARYREPO beneficiaryRepo;

    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoService;

    public List<BENEFICIARYREQUEST> getBeneficiaryDetailsByPolicyNo(String policyNo) throws ValueNotExistException, BadRequestRuntimeException {
        if (policyNo == null || policyNo.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<PG_BENEFICIARY> beneficiaries = beneficiaryRepo.findByPolicyNo(policyNo);
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
    public List<BirthdaysResponse> getBeneficiaryBirthdays(String agntnum, Date startDate, Date endDate) throws ValueNotExistException {
        List<Object[]> result = beneficiaryRepo.findBirthdaysByAgentNumber(agntnum, startDate, endDate);
        List<BirthdaysResponse> birthdaysResponseList = new ArrayList<>();
        HashSet<DuplicateKey> processedKeys = new HashSet<>(); // To keep track of processed keys
        for (Object[] objects : result) {
            PG_BENEFICIARY beneficiaryEntity = (PG_BENEFICIARY) objects[0];
            PG_CLIENTINFO clientInfoEntity = (PG_CLIENTINFO) objects[1];
            String clientId = clientInfoEntity.getCLIENT_NO();
            String name = beneficiaryEntity.getNAME();

            // Create a custom key object containing CLIENT_NO and NAME
            DuplicateKey key = new DuplicateKey(clientId, name);

            // Check if this key is already processed
            if (!processedKeys.add(key)) {
                // If the entry is a duplicate, skip adding it to the response list
                continue;
            }

            // Construct BirthdaysResponse object
            BirthdaysResponse response = new BirthdaysResponse(beneficiaryEntity, clientInfoEntity);
            response.setClient_No(clientId);
            birthdaysResponseList.add(response);
        }
        if (birthdaysResponseList.isEmpty()) {
            throw new ValueNotExistException("No beneficiaries found with birthdays between the given date range");
        }
        return birthdaysResponseList;
    }

    // Define a custom key object to hold CLIENT_NO and NAME
    class DuplicateKey {
        private String clientId;
        private String name;
        private Date dob;

        public DuplicateKey(String clientId, String name) {
            this.clientId = clientId;
            this.name = name;
            this.dob=dob;

        }

        // Override hashCode and equals method to ensure proper functioning of HashSet
        @Override
        public int hashCode() {
            return Objects.hash(clientId, name,dob);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DuplicateKey other = (DuplicateKey) obj;
            return Objects.equals(clientId, other.clientId) && Objects.equals(name, other.name) && Objects.equals(dob, other.dob);
        }
    }}



