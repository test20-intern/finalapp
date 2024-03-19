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
    @Query(nativeQuery = true, value = "EXEC SalesApp_Select_ReceiptsForPolicyNo @POLICY_NO=:POLICY_NO,@userType=:userType")
    List<Object[]> findReceiptDataByPolicyNo(
            @Param("POLICY_NO") String POLICY_NO,
            @Param("userType")String userType
    );



    // Query for 'getAgentReceipts
    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_ReceiptDetailsForAgent @agntnum=:agntnum,@policyNo=:policyNo,@startDate=:startDate,@endDate=:endDate,@userType=:userType")
    List<Object[]> getAgentReceipts(
            @Param("agntnum") String agntnum,
            @Param("policyNo") String policyNo,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("userType")String userType
    );







}

