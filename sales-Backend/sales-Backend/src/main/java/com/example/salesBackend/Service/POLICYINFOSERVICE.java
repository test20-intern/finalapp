package com.example.salesBackend.Service;

import com.example.salesBackend.Repo.PG_POLICYINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POLICYINFOSERVICE {

    @Autowired
    private PG_POLICYINFOREPO pgPolicyInfoRepo;

    public List<Object[]> getPolicyDetailsWithClientName() {
        try {
            return pgPolicyInfoRepo.getPolicyDetailsWithClientName();
        } catch (Exception e) {
            // Handle exceptions here
            throw new RuntimeException("Error retrieving policy details with client name", e);
        }
    }

    public List<Object[]> getPolicyDetailsWithSearchParams(String POLICY_NO, String NIC, String NAME, String CLIENT_NO, String AGNTNUM) {
        try {
            return pgPolicyInfoRepo.getPolicyDetailsWithSearchParams(POLICY_NO, NIC, NAME, CLIENT_NO, AGNTNUM);
        } catch (Exception e) {
            // Handle exceptions here
            throw new RuntimeException("Error retrieving policy details with search parameters", e);
        }
    }

    public List<Object[]> getPolicyBenefitDetailsByPolicyNo(String POLICY_NO) {
        try {
            return pgPolicyInfoRepo.getPolicyBenefitDetailsByPolicyNo(POLICY_NO);
        } catch (Exception e) {
            // Handle exceptions here
            throw new RuntimeException("Error retrieving policy benefit details by policy number", e);
        }
    }

    public List<Object[]> getPolicyColumns(String POLICY_NO) {
        try {
            return pgPolicyInfoRepo.getPolicyColumns(POLICY_NO);
        } catch (Exception e) {
            // Handle exceptions here
            throw new RuntimeException("Error retrieving policy columns by policy number", e);
        }
    }
}
