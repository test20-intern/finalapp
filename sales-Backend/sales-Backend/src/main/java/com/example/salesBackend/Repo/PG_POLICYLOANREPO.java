package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Entity.PG_POLICYLOAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PG_POLICYLOANREPO extends JpaRepository<PG_POLICYLOAN,String> {
    @Query("SELECT l " +
            "FROM PG_POLICYLOAN l " +
            "JOIN PG_POLICYINFO p ON l.POLICY_NO = p.POLICY_NO " +
            "WHERE p.POLICY_NO = :POLICY_NO")
    List<PG_POLICYLOAN> getLoanDetailsByPolicyNo(@Param("POLICY_NO") String POLICY_NO);
}
