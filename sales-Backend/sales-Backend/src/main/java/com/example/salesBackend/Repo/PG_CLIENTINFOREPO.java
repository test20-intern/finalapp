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



    @Query("SELECT c FROM PG_CLIENTINFO c " +
            "JOIN PG_POLICYINFO p ON c.CLIENT_NO = p.CLIENT_NO " +
            "WHERE p.AGNTNUM = :agntnum " +
            "AND MONTH(c.DOB) = MONTH(:startDate) " +
            "AND DAY(c.DOB) >= DAY(:startDate) " +
            "AND DAY(c.DOB) <= DAY(:endDate)")
    List<PG_CLIENTINFO> findByAgntnumAndDobBetween(
            @Param("agntnum") String agntnum,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );





}

