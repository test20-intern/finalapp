package com.example.salesBackend.Service;

import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_BENEFITREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BENEFITSERVICE {
    private final PG_BENEFITREPO benefitRepository;

    public BENEFITSERVICE(PG_BENEFITREPO benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    public List<Object[]> getBenefitDetailsByPolicyNo(String policyNo,String userType) throws ValueNotExistException, BadRequestRuntimeException {
        if (policyNo == null || policyNo.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<Object[]> benefitDetails = benefitRepository.getBenefitDetailsByPolicyNo(policyNo,userType);
        if (benefitDetails.isEmpty()) {
            throw new ValueNotExistException("Benefit details not found for policy number: " + policyNo);
        }

        return benefitDetails;
    }

}
