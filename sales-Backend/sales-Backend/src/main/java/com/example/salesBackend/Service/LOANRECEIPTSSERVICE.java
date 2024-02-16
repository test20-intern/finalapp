
package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.LOANRECEIPTREQUEST;
import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Entity.PG_LOANRECEIPTS;
import com.example.salesBackend.Repo.PG_LOANRECEIPTSREPO;
import com.example.salesBackend.Repo.PG_RECEIPTSREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LOANRECEIPTSSERVICE {

    @Autowired
    private PG_LOANRECEIPTSREPO pgloanReceiptsRepo;

    public List<LOANRECEIPTREQUEST> getloanReceiptDetailsByPolicyNo(String POLICY_NO) {
        return pgloanReceiptsRepo.findLoanReceiptByPolicyNo(POLICY_NO);
    }

}


