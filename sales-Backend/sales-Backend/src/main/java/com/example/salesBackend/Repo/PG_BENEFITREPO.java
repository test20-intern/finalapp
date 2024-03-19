package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_BENEFIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PG_BENEFITREPO extends JpaRepository<PG_BENEFIT,String> {


    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_BenefitDetailsByPolicyNo @policyNo = :policyNo,@userType=:userType")
    List<Object[]> getBenefitDetailsByPolicyNo(
            @Param("policyNo") String policyNo,
            @Param("userType") String userType
    );






}
