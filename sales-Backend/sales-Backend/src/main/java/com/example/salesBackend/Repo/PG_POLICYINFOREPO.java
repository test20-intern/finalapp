package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_POLICYINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PG_POLICYINFOREPO extends JpaRepository<PG_POLICYINFO, String> {


/*Query to search the policy details when search by Policy number, NIC, Name( Name in ClientInfo table)
   Or client Number */
@Query("SELECT p.POLICY_NO, c.NAME, p.PREMIUM, p.POLICY_STATUS, p.AGNTNUM " +
        "FROM PG_POLICYINFO p " +
        "JOIN PG_CLIENTINFO c ON p.CLIENT_NO = c.CLIENT_NO " +
        "WHERE (?1 IS NULL OR p.POLICY_NO = ?1) " +
        "AND (?2 IS NULL OR c.NIC LIKE CONCAT(?2, '%')) " +
        "AND (?3 IS NULL OR c.NAME LIKE %?3%) " +
        "AND (?4 IS NULL OR p.CLIENT_NO LIKE %?4%)"+
        "AND (?5 IS NULL OR p.AGNTNUM LIKE %?5%)"
)


List<Object[]> getPolicyDetailsWithSearchParams(
        @Param("POLICY_NO") String POLICY_NO,
        @Param("NIC") String NIC,
        @Param("NAME") String NAME,
        @Param("CLIENT_NO") String CLIENT_NO,
        @Param("AGNTNUM") String AGNTNUM);

    @Query("SELECT p.POLICY_NO, p.PREMIUM, p.TOTAL_DUE, p.SUNDRY_BALANCE, p.PAIDUP_DATE, " +
            "p.PLAN_NAME, p.PAYMENT_MODE, p.SUM_ASSURED, p.RISK_DATE, p.TERM " +
            "FROM PG_POLICYINFO p " +
            "WHERE (?1 IS NULL OR p.POLICY_NO = ?1)")
    List<Object[]> getPolicyColumns(@Param("POLICY_NO") String POLICY_NO);



    /* Query for when user want to see all the policy details. By directly clicking "Search"*/
    @Query("SELECT p.POLICY_NO, c.NAME, p.PREMIUM, p.POLICY_STATUS,p.AGNTNUM " +
            "FROM PG_POLICYINFO p " +
            "JOIN PG_CLIENTINFO c ON p.CLIENT_NO = c.CLIENT_NO")
    List<Object[]> getPolicyDetailsWithClientName();


    @Query("SELECT p FROM PG_POLICYINFO p WHERE p.AGNTNUM = :agntnum AND p.PAIDUP_DATE BETWEEN :startDate AND :endDate")
    List<PG_POLICYINFO> findDuePoliciesByAgntnumAndPaidupDateBetween(
            @Param("agntnum") String agntnum,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
}








