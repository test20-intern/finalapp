package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PG_CLIENTINFOREPO extends JpaRepository<PG_CLIENTINFO, String> {

    // Query to get client details based on the POLICY_NO
    @Query("SELECT c " +
            "FROM PG_CLIENTINFO c " +
            "JOIN PG_POLICYINFO p ON c.CLIENT_NO = p.CLIENT_NO " +
            "WHERE p.POLICY_NO = :POLICY_NO")
    List<PG_CLIENTINFO> getClientDetailsByPolicyNo(@Param("POLICY_NO") String POLICY_NO);
}
