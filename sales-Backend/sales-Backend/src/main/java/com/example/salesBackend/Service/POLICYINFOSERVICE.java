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


    public List<Object[]> getPolicyDetailsWithSearchParams(String policyNo, String nic, String clientName, String clientId,String AGNTNUM) {
        List<Object[]> result = pgPolicyInfoRepo.getPolicyDetailsWithSearchParams(policyNo, nic, clientName, clientId, AGNTNUM);


        return result;

    }

}

