package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PG_CLIENTINFOREPO extends JpaRepository<PG_CLIENTINFO, String> {

    // Query to get client details based on the POLICY_NO
    @Query("SELECT c " +
            "FROM PG_CLIENTINFO c " +
            "JOIN PG_POLICYINFO p ON c.CLIENT_NO = p.CLIENT_NO " +
            "WHERE p.POLICY_NO = :POLICY_NO")
    List<PG_CLIENTINFO> getClientDetailsByPolicyNo(@Param("POLICY_NO") String POLICY_NO);



    @Query(value = "SELECT ci.* FROM PG_CLIENTINFO ci " +
            "INNER JOIN PG_POLICYINFO pi ON ci.CLIENT_NO = pi.CLIENT_NO " +
            "WHERE pi.AGNTNUM = :agentNumber " +
            "AND ci.DOB <> '99999999' " +
            "AND MONTH(CONVERT(DATE, CONVERT(VARCHAR(8), CONVERT(INT, ci.DOB)))) * 100 + DAY(CONVERT(DATE, CONVERT(VARCHAR(8), CONVERT(INT, ci.DOB)))) " +
            "BETWEEN MONTH(:startDate) * 100 + DAY(:startDate) AND MONTH(:endDate) * 100 + DAY(:endDate) " +
            "ORDER BY MONTH(CONVERT(DATE, CONVERT(VARCHAR(8), CONVERT(INT, ci.DOB)))), DAY(CONVERT(DATE, CONVERT(VARCHAR(8), CONVERT(INT, ci.DOB))))",
            nativeQuery = true)
    List<PG_CLIENTINFO> findClientInfoByAgentAndDateRange(@Param("agentNumber") String agentNumber,
                                                       @Param("startDate") Date startDate,
                                                       @Param("endDate") Date endDate);







}

