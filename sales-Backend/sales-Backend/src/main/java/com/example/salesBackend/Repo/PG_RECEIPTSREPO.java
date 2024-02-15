package com.example.salesBackend.Repo;

import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Entity.PG_RECEIPTS;
import com.example.salesBackend.Entity.PG_RECEIPTSId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PG_RECEIPTSREPO extends JpaRepository<PG_RECEIPTS, PG_RECEIPTSId> {
    @Query("SELECT new com.example.salesBackend.Dto.Request.RECEIPTREQUEST(" +
            "r.RID.POLICY_NO,r.RID.RECEIPT_NO, r.RECEIPT_DATE, r.RID.AMOUNT) " +
            "FROM PG_RECEIPTS r " +
            "WHERE r.RID.POLICY_NO = :POLICY_NO " +
            "ORDER BY r.RECEIPT_DATE DESC")
    List<RECEIPTREQUEST> findReceiptByPolicyNo(String POLICY_NO);
}

