package com.example.salesBackend.Repo;

import com.example.salesBackend.Dto.Response.BirthdaysResponse;
import com.example.salesBackend.Entity.BeneficiaryId;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PG_BENEFICIARYREPO extends JpaRepository<PG_BENEFICIARY, BeneficiaryId> {

    @Query(nativeQuery = true, value = "EXEC SalesApp_Select_BeneficiaryForPolicyNo @POLICY_NO=:policyNo, @userType=:userType")
    List<PG_BENEFICIARY> findByPolicyNo(
            @Param("policyNo") String policyNo,
            @Param("userType") String userType
    );

    @Query( nativeQuery = true, value = "EXEC SalesApp_Test_Select_BeneficiaryBirthdayForAgentNo @startDate=:startDate , @endDate=:endDate,@GroupCode=:GroupCode,@BranchCode=:BranchCode,@UnitCode=:UnitCode,@agntnum=:agntnum, @userType=:userType ")
    List<Object[]> findBirthdaysByAgentNumber(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("agntnum") String agntnum,
            @Param("GroupCode") String GroupCode,
            @Param("BranchCode") String BranchCode,
            @Param("UnitCode") String UnitCode,
            @Param("userType") String userType
    );





}
