package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.BeneficiaryId;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PG_BENEFICIARYREPO extends JpaRepository<PG_BENEFICIARY, BeneficiaryId> {

    @Query("SELECT pb FROM PG_BENEFICIARY pb JOIN PG_POLICYINFO pp ON pb.BID.CUSTOMERID = pp.CLIENT_NO WHERE pp.POLICY_NO = :policyNo")
    List<PG_BENEFICIARY> findByPolicyNo(@Param("policyNo") String policyNo);
}
