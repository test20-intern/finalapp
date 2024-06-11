package com.example.salesBackend.Service;

import com.example.salesBackend.Repo.PG_CLIENTINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDetailsForDiary {

    @Autowired
    private PG_CLIENTINFOREPO pgClientInfoRepo;
    public List<Object[]> getClientCitiesAndNamesForDiary(String agntnum, String city) {
        return pgClientInfoRepo.findClientCityAndNameByAgentNumberForDiary( agntnum, city);
    }

}
