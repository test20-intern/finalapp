package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_RECEIPTSREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RECEIPTSSERVICE {

    @Autowired
    private PG_RECEIPTSREPO pgReceiptsRepo;

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
}
