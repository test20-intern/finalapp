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

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RECEIPTSSERVICE {

    @Autowired
    private PG_RECEIPTSREPO pgReceiptsRepo;
    @Autowired
    private PG_POLICYINFOREPO pgPolicyInfoRepo;

    public List<RECEIPTREQUEST> getReceiptDetailsByPolicyNo(String POLICY_NO,String userType) throws ValueNotExistException {
        if (POLICY_NO == null || POLICY_NO.isEmpty()) {
            throw new IllegalArgumentException("Policy number cannot be null or empty");
        }

        List<Object[]> receiptData = pgReceiptsRepo.findReceiptDataByPolicyNo(POLICY_NO,userType);
        if (receiptData.isEmpty()) {
            throw new ValueNotExistException("Receipt details not found for policy number: " + POLICY_NO);
        }

        List<RECEIPTREQUEST> receiptRequests = new ArrayList<>();
        for (Object[] row : receiptData) {
            RECEIPTREQUEST request = new RECEIPTREQUEST();
            request.setPOLICY_NO((String) row[0]);
            request.setRECEIPT_NO((String) row[1]);
            request.setRECEIPT_DATE((Date) row[2]);
            request.setAMOUNT((BigDecimal) row[3]);
            receiptRequests.add(request);
        }
        return receiptRequests;
    }

    //code snippet for getAgentReceipts API.
    public List<Map<String, Object>> getAgentReceiptsMapped(String agntnum, String policyNo, Date startDate, Date endDate,String userType) {
        List<Object[]> results = pgReceiptsRepo.getAgentReceipts(agntnum, policyNo, startDate, endDate,userType);
        List<Map<String, Object>> mappedResults = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> mappedRow = new HashMap<>();

            // Mapping RID data
            Map<String, Object> ridData = new HashMap<>();
            ridData.put("amount", row[3]);
            ridData.put("policy_NO", row[4]);
            ridData.put("receipt_NO", row[1]);
            mappedRow.put("rid", ridData);

            // Mapping PG_RECEIPTS data
            mappedRow.put("receipt_DATE", row[2]);

            // Mapping PG_CLIENTINFO data
            mappedRow.put("term", row[11]);
            mappedRow.put("agntnum", row[20]);
            mappedRow.put("branch", row[14]);
            mappedRow.put("premium", row[15]);
            mappedRow.put("policy_NO", row[4]);
            mappedRow.put("sum_ASSURED", row[9]);
            mappedRow.put("risk_DATE", row[10]);
            mappedRow.put("paidup_DATE", row[11]);
            mappedRow.put("policy_STATUS", row[19]);
            mappedRow.put("interest", row[16]);
            mappedRow.put("client_NO", row[5]);
            mappedRow.put("premium_DUE", row[17]);
            mappedRow.put("sundry_BALANCE", row[18]);
            mappedRow.put("prem_CESS_DATE", row[12]);
            mappedRow.put("total_DUE", row[13]);
            mappedRow.put("plan_NAME", row[7]);
            mappedRow.put("account_BALANCE", row[8]);
            mappedRow.put("payment_MODE", row[6]);
            mappedRow.put("name", row[6]);

            mappedResults.add(mappedRow);
        }

        return mappedResults;
    }




}
