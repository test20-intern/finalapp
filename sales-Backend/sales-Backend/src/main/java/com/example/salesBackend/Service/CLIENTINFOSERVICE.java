package com.example.salesBackend.Service;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Repo.PG_CLIENTINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CLIENTINFOSERVICE {

    private static int temporaryIdCounter = 1;

    @Autowired
    private PG_CLIENTINFOREPO pgClientInfoRepo;

    public List<PG_CLIENTINFO> getAllClients() {
        return pgClientInfoRepo.findAll();
    }

    public List<PG_CLIENTINFO> getClientDetailsByPolicyNo(String POLICY_NO) {
        try {
            return pgClientInfoRepo.getClientDetailsByPolicyNo(POLICY_NO);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirements
            throw new RuntimeException("Error retrieving client details by policy number", e);
        }
    }

    private List<PG_CLIENTINFO> addTemporaryIdToResults(List<PG_CLIENTINFO> clientDetails) {
        // Assuming you still need the temporaryId logic
        List<PG_CLIENTINFO> resultsWithTemporaryId = new ArrayList<>();
        for (PG_CLIENTINFO clientDetail : clientDetails) {
            PG_CLIENTINFO resultWithId = new PG_CLIENTINFO();
            resultWithId.setId(temporaryIdCounter++);
            resultWithId.setNIC(clientDetail.getNIC());
            resultWithId.setNAME(clientDetail.getNAME());

            resultsWithTemporaryId.add(resultWithId);
        }
        return resultsWithTemporaryId;
    }
}
