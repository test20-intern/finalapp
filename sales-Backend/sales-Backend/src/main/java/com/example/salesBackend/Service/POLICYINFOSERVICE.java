package com.example.salesBackend.Service;

import com.example.salesBackend.Repo.PG_POLICYINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class POLICYINFOSERVICE {


    private static int temporaryIdCounter = 1;

    @Autowired
    private PG_POLICYINFOREPO pgPolicyInfoRepo;






    public List<Object[]> getPolicyDetailsWithClientName() {
        return pgPolicyInfoRepo.getPolicyDetailsWithClientName();
    }


    public List<Object[]> getPolicyDetailsWithSearchParams(String POLICY_NO, String NIC, String NAME, String CLIENT_NO,String AGNTNUM) {
        List<Object[]> result = pgPolicyInfoRepo.getPolicyDetailsWithSearchParams(POLICY_NO, NIC, NAME, CLIENT_NO, AGNTNUM);


        return result;

    }

}

