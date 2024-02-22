package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_BENEFIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PG_BENEFITREPO extends JpaRepository<PG_BENEFIT,String> {
    @Query("SELECT b.LIFENO, b.BENEFIT_CODE, bm.DESCRIPTION, b.COVER_AMOUNT " +
            "FROM PG_BENEFIT b " +
            "JOIN PG_BENEFIT_MASTER bm ON b.BENEFIT_CODE = bm.BENEFIT_CODE " +
            "WHERE b.POLICY_NO = :POLICY_NO")
    List<Object[]> getBenefitDetailsByPolicyNo(@Param("POLICY_NO") String policyNo);
}
