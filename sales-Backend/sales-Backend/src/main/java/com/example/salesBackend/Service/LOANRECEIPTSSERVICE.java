
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

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LOANRECEIPTSSERVICE {

    @Autowired
    private PG_LOANRECEIPTSREPO pgloanReceiptsRepo;


    public List<LOANRECEIPTREQUEST> getLoanReceiptDetailsByPolicyNo(String POLICY_NO,String userType) throws BadRequestRuntimeException, ValueNotExistException {
        if (POLICY_NO == null || POLICY_NO.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<Object[]> loanReceiptData = pgloanReceiptsRepo.findLoanReceiptsDataByPolicyNo(POLICY_NO,userType);
        if (loanReceiptData.isEmpty()) {
            throw new ValueNotExistException("Loan receipt details not found for policy number: " + POLICY_NO);
        }

        return loanReceiptData.stream()
                .map(row -> {
                    LOANRECEIPTREQUEST request = new LOANRECEIPTREQUEST();
                    request.setPOLICY_NO((String) row[0]);
                    request.setRECEIPT_NO((String) row[1]);
                    request.setLOAN_NO((String) row[2]);
                    request.setRECEIPT_DATE((Date) row[3]);
                    request.setAMOUNT((BigDecimal) row[4]);
                    return request;
                })
                .collect(Collectors.toList());
    }
}



