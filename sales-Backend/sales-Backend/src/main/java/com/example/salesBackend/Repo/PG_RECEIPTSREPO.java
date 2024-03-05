package com.example.salesBackend.Repo;

import com.example.salesBackend.Dto.Request.RECEIPTREQUEST;
import com.example.salesBackend.Entity.PG_RECEIPTS;
import com.example.salesBackend.Entity.PG_RECEIPTSId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PG_RECEIPTSREPO extends JpaRepository<PG_RECEIPTS, PG_RECEIPTSId> {
    @Query("SELECT new com.example.salesBackend.Dto.Request.RECEIPTREQUEST(" +
            "r.RID.POLICY_NO,r.RID.RECEIPT_NO, r.RECEIPT_DATE, r.RID.AMOUNT) " +
            "FROM PG_RECEIPTS r " +
            "WHERE r.RID.POLICY_NO = :POLICY_NO " +
            "ORDER BY r.RECEIPT_DATE DESC")
    List<RECEIPTREQUEST> findReceiptByPolicyNo(String POLICY_NO);


    // Query for 'getAgentReceipts
    @Query("SELECT r, p " +
            "FROM PG_RECEIPTS r " +
            "JOIN PG_POLICYINFO p ON r.RID.POLICY_NO = p.POLICY_NO " +
            "WHERE p.AGNTNUM = :agntnum " +
            "AND (:policyNo IS NULL OR p.POLICY_NO = :policyNo) " +
            "AND r.RECEIPT_DATE BETWEEN :startDate AND :endDate")
    List<Object[]> getAgentReceipts(
            @Param("agntnum") String agntnum,
            @Param("policyNo") String policyNo,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );






}

