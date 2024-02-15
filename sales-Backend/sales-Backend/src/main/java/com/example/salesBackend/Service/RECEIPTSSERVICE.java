package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Repo.PG_RECEIPTSREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RECEIPTSSERVICE {

    @Autowired
    private PG_RECEIPTSREPO pgReceiptsRepo;

    public List<RECEIPTREQUEST> getReceiptDetailsByPolicyNo(String POLICY_NO) {
        return pgReceiptsRepo.findReceiptByPolicyNo(POLICY_NO);
    }
}
