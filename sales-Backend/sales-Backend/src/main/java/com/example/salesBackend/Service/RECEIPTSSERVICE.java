package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Entity.PG_POLICYINFO;
import com.example.salesBackend.Entity.PG_RECEIPTS;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_POLICYINFOREPO;
import com.example.salesBackend.Repo.PG_RECEIPTSREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RECEIPTSSERVICE {

    @Autowired
    private PG_RECEIPTSREPO pgReceiptsRepo;
    @Autowired
    private PG_POLICYINFOREPO pgPolicyInfoRepo;

    public List<RECEIPTREQUEST> getReceiptDetailsByPolicyNo(String POLICY_NO) throws ValueNotExistException, BadRequestRuntimeException {
        if (POLICY_NO == null || POLICY_NO.isEmpty()) {
            throw new BadRequestRuntimeException("Policy number cannot be null or empty");
        }

        List<RECEIPTREQUEST> receiptDetails = pgReceiptsRepo.findReceiptByPolicyNo(POLICY_NO);
        if (receiptDetails.isEmpty()) {
            throw new ValueNotExistException("Receipt details not found for policy number: " + POLICY_NO);
        }
        return receiptDetails;
    }


    //code snippet for getAgentReceipts API.
    public List<Object[]> getAgentReceipts(String agntnum, String policyNo, Date startDate, Date endDate) {
        return pgReceiptsRepo.getAgentReceipts(agntnum, policyNo, startDate, endDate);
    }




}
