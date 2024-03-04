package com.example.salesBackend.Service;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_CLIENTINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CLIENTINFOSERVICE {

    private static int temporaryIdCounter = 1;

    @Autowired
    private PG_CLIENTINFOREPO pgClientInfoRepo;

    public List<PG_CLIENTINFO> getClientDetailsByPolicyNo(String POLICY_NO) throws ValueNotExistException, BadRequestRuntimeException {
        if (POLICY_NO == null || POLICY_NO.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<PG_CLIENTINFO> clientDetails = pgClientInfoRepo.getClientDetailsByPolicyNo(POLICY_NO);
        if (clientDetails.isEmpty()) {
            throw new ValueNotExistException("Client details not found for policy number: " + POLICY_NO);
        }

        return clientDetails;
    }

    public List<PG_CLIENTINFO> getClientBirthdays(String agntnum, Date startDate, Date endDate) {
        try {
            List<PG_CLIENTINFO> clientInfoList = pgClientInfoRepo.findByAgntnumAndDobBetween(agntnum, startDate, endDate);

            if (clientInfoList.isEmpty()) {
                throw new ValueNotExistException("No client birthdays found for the specified criteria.");
            }

            return clientInfoList;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching client birthdays: " + e.getMessage());
        }
    }




}
