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
    private PG_CLIENTINFOREPO PG_CLIENTINFOREPO;
    public List<PG_CLIENTINFO> getAllClients() {
        return PG_CLIENTINFOREPO.findAll();
    }

    private List<PG_CLIENTINFO> addTemporaryIdToResults(List<PG_CLIENTINFO> clientDetails) {
        List<PG_CLIENTINFO> resultsWithTemporaryId = new ArrayList<>();
        for (PG_CLIENTINFO clientDetail : clientDetails) {
            PG_CLIENTINFO resultWithId = new PG_CLIENTINFO();
            resultWithId.setId(temporaryIdCounter++);
            resultWithId.setNIC(clientDetail.getNIC());
            resultWithId.setNAME(clientDetail.getNAME());
            // Add other properties as needed
            resultsWithTemporaryId.add(resultWithId);
        }
        return resultsWithTemporaryId;
    }

}
