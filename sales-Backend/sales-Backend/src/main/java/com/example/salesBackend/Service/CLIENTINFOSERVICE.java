package com.example.salesBackend.Service;


import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_CLIENTINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CLIENTINFOSERVICE {

    private static int temporaryIdCounter = 1;

    @Autowired
    private PG_CLIENTINFOREPO pgClientInfoRepo;



    public List<PG_CLIENTINFO> getClientDetailsByPolicyNo(String POLICY_NO,String userType) throws ValueNotExistException, BadRequestRuntimeException {
        if (POLICY_NO == null || POLICY_NO.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<PG_CLIENTINFO> clientDetails = pgClientInfoRepo.getClientDetailsByPolicyNo(POLICY_NO,userType);
        if (clientDetails.isEmpty()) {
            throw new ValueNotExistException("Client details not found for policy number: " + POLICY_NO);
        }

        return clientDetails;
    }

    public List<Object[]> getClientInfoByAgentAndDateRange(String agentNumber, Date startDate, Date endDate, String userType, String groupCode, String branchCode, String unitCode) throws ValueNotExistException {
        List<Object[]> clientInfoList = pgClientInfoRepo.findClientInfoByAgentAndDateRange(startDate, endDate, groupCode, branchCode, unitCode, agentNumber, userType);

        if (clientInfoList.isEmpty()) {
            throw new ValueNotExistException("No client information found for the specified criteria.");
        }

        return clientInfoList;
    }

//    public List<ClientCityDTO> getClientCity(String groupCode, String branchCode, String unitCode, String agntnum, String userType) {
//        return pgClientInfoRepo.getClientCity(groupCode, branchCode, unitCode, agntnum, userType);
//    }






}
