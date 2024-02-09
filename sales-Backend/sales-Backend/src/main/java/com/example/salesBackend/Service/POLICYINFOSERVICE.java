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


    public List<Object[]> getPolicyDetailsWithSearchParams(String policyNo, String nic, String clientName, String clientId) {
        List<Object[]> result = pgPolicyInfoRepo.getPolicyDetailsWithSearchParams(policyNo, nic, clientName, clientId);
        // Set temporaryId for each result item
        IntStream.range(0, result.size()).forEach(i -> {
            Object[] item = result.get(i);
            item[0] = (long) i + 1; // Set temporaryId
        });
        return result;
    }

}

