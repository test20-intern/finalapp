package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.BENEFICIARYREQUEST;
import com.example.salesBackend.Dto.Response.BirthdaysResponse;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_BENEFICIARYREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BENEFICIARYSERVICE {

    @Autowired
    private PG_BENEFICIARYREPO beneficiaryRepo;

    @Autowired
    private POLICYINFOSERVICE pgPolicyInfoService;

    public List<BENEFICIARYREQUEST> getBeneficiaryDetailsByPolicyNo(String policyNo) throws ValueNotExistException, BadRequestRuntimeException {
        if (policyNo == null || policyNo.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<PG_BENEFICIARY> beneficiaries = beneficiaryRepo.findByPolicyNo(policyNo);
        if (beneficiaries.isEmpty()) {
            throw new ValueNotExistException("Beneficiary details not found for policy number: " + policyNo);
        }

        return beneficiaries.stream()
                .map(this::mapEntityToRequest)
                .collect(Collectors.toList());
    }

    private BENEFICIARYREQUEST mapEntityToRequest(PG_BENEFICIARY beneficiary) {
        BENEFICIARYREQUEST request = new BENEFICIARYREQUEST();
        request.setRELATIONSHIP(beneficiary.getBID().getRELATIONSHIP());
        request.setNAME(beneficiary.getNAME());
        request.setDOB(beneficiary.getDOB());
        request.setPERCENTAGE(beneficiary.getPERCENTAGE());
        return request;
    }



    public List<BirthdaysResponse> getBeneficiaryBirthdays(String agntnum, Date startDate, Date endDate) throws ValueNotExistException {
        List<BirthdaysResponse> birthdaysResponseList = beneficiaryRepo.findBirthdaysByAgentNumber(agntnum, startDate, endDate);

        if (birthdaysResponseList.isEmpty()) {
            throw new ValueNotExistException("No beneficiaries found with birthdays between the given date range");
        }

        return birthdaysResponseList;
    }
}
