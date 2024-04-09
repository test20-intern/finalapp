package com.example.salesBackend.Service;

import com.example.salesBackend.Entity.PG_POLICYLOAN;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_POLICYLOANREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POLICYLOANSERVICE {

    private static int temporaryIdCounter = 1;

    @Autowired
    private PG_POLICYLOANREPO pgPolicyloanrepo;

    public List<PG_POLICYLOAN> getLoanDetailsByPolicyNo(String POLICY_NO,String userType) throws ValueNotExistException, BadRequestRuntimeException {
        if (POLICY_NO == null || POLICY_NO.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<PG_POLICYLOAN> loanDetails = pgPolicyloanrepo.getLoanDetailsByPolicyNo(POLICY_NO,userType);
        if (loanDetails.isEmpty()) {
            throw new ValueNotExistException("Loan details not found for policy number: " + POLICY_NO);
        }

        return loanDetails;
    }
}
