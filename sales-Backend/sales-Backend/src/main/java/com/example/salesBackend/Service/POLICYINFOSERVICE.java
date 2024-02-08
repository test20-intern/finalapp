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
        return pgPolicyInfoRepo.getPolicyDetailsWithClientName();
    }
}
