package com.example.salesBackend.Service;


import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Entity.PG_POLICYLOAN;
import com.example.salesBackend.Repo.PG_CLIENTINFOREPO;
import com.example.salesBackend.Repo.PG_POLICYLOANREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POLICYLOANSERVICE {

    private static int temporaryIdCounter = 1;

    @Autowired
    private PG_POLICYLOANREPO pgPolicyloanrepo;




    public List<PG_POLICYLOAN> getLoanDetailsByPolicyNo(String POLICY_NO) {
        try {
            return pgPolicyloanrepo.getLoanDetailsByPolicyNo(POLICY_NO);
        } catch (Exception e) {

            throw new RuntimeException("Error retrieving loan details by policy number", e);
        }
    }
}
