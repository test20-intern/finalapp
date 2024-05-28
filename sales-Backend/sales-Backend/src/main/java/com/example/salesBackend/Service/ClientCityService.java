package com.example.salesBackend.Service;

import com.example.salesBackend.Repo.PG_CLIENTINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientCityService {
    @Autowired
    private PG_CLIENTINFOREPO pgClientInfoRepo;

    public List<Object[]> getClientCities(String groupCode, String branchCode, String unitCode, String agntnum, String city, String userType) {
        return pgClientInfoRepo.findClientCityByAgentNumber(groupCode, branchCode, unitCode, agntnum, city, userType);
    }


}
