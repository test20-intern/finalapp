package com.example.salesBackend.Service;

import com.example.salesBackend.Repo.ProspectDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectDetailService {

    @Autowired
    private ProspectDetailRepo prospectDetailRepo;

    public List<Object[]> getSuspectsByAgentNumberForDiary(String agntnum) {
        return prospectDetailRepo.findSuspectsByAgentNumberForDiary( agntnum);
    }

    public List<Object[]> getProspectsByAgentNumberForDiary(String agntnum) {
        return prospectDetailRepo.findProspectsByAgentNumberForDiary( agntnum);
    }

}
