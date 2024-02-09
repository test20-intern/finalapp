package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_POLICYINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PG_POLICYINFOREPO extends JpaRepository<PG_POLICYINFO, String> {


/*Query to search the policy details when search by Policy number, NIC,Client Name( Name in ClientInfo table)
   Or client Number */
@Query("SELECT p.POLICY_NO, c.NAME, p.PREMIUM, p.POLICY_STATUS " +
        "FROM PG_POLICYINFO p " +
        "JOIN PG_CLIENTINFO c ON p.CLIENT_NO = c.CLIENT_NO " +
        "WHERE (?1 IS NULL OR p.POLICY_NO = ?1) " +
        "AND (?2 IS NULL OR c.NIC LIKE %?2%) " +
        "AND (?3 IS NULL OR c.NAME LIKE %?3%) " +
        "AND (?4 IS NULL OR p.CLIENT_NO LIKE %?4%)")
List<Object[]> getPolicyDetailsWithSearchParams(
        @Param("policyNo") String policyNo,
        @Param("nic") String nic,
        @Param("clientName") String clientName,
        @Param("clientId") String clientId);


    /* Query for when user want to see all the policy details. By directly clicking "Search"*/
    @Query("SELECT p.POLICY_NO, c.NAME, p.PREMIUM, p.POLICY_STATUS " +
            "FROM PG_POLICYINFO p " +
            "JOIN PG_CLIENTINFO c ON p.CLIENT_NO = c.CLIENT_NO")
    List<Object[]> getPolicyDetailsWithClientName();
}
