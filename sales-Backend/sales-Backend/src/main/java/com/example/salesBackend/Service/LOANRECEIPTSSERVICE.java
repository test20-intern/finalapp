
package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.LOANRECEIPTREQUEST;
import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Entity.PG_LOANRECEIPTS;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_LOANRECEIPTSREPO;
import com.example.salesBackend.Repo.PG_RECEIPTSREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LOANRECEIPTSSERVICE {

    @Autowired
    private PG_LOANRECEIPTSREPO pgloanReceiptsRepo;

//    public List<LOANRECEIPTREQUEST> getloanReceiptDetailsByPolicyNo(String POLICY_NO) {
//        return pgloanReceiptsRepo.findLoanReceiptByPolicyNo(POLICY_NO);
//    }

    public List<LOANRECEIPTREQUEST> getloanReceiptDetailsByPolicyNo(String POLICY_NO) throws ValueNotExistException, BadRequestRuntimeException {
        if (POLICY_NO == null || POLICY_NO.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<LOANRECEIPTREQUEST> loanReceipts = pgloanReceiptsRepo.findLoanReceiptByPolicyNo(POLICY_NO);
        if (loanReceipts.isEmpty()) {
            throw new ValueNotExistException("Loan receipt details not found for policy number: " + POLICY_NO);
        }

        return loanReceipts;
    }

}


