package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_POLICYINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PG_POLICYINFOREPO extends JpaRepository<PG_POLICYINFO, String> {
    @Query("SELECT p.POLICY_NO, c.NAME, p.PREMIUM, p.POLICY_STATUS " +
            "FROM PG_POLICYINFO p " +
            "JOIN PG_CLIENTINFO c ON p.CLIENT_NO = c.CLIENT_NO")
    List<Object[]> getPolicyDetailsWithClientName();
}

