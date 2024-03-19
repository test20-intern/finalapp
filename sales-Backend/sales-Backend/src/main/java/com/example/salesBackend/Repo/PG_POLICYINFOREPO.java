package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_POLICYINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PG_POLICYINFOREPO extends JpaRepository<PG_POLICYINFO, String> {


/*Query to search the policy details when search by Policy number, NIC, Name( Name in ClientInfo table)
   Or client Number */
@Query(nativeQuery = true, value = "EXEC SalesApp_Select_PolicyDeatilsForAgentNumberAndParamaters " +
        "@POLICY_NO = :POLICY_NO, " +
        "@NIC = :NIC, " +
        "@NAME = :NAME, " +
        "@CLIENT_NO = :CLIENT_NO, " +
        "@AGNTNUM = :AGNTNUM")
List<Object[]> getPolicyDetailsWithSearchParams(
        @Param("POLICY_NO") String POLICY_NO,
        @Param("NIC") String NIC,
        @Param("NAME") String NAME,
        @Param("CLIENT_NO") String CLIENT_NO,
        @Param("AGNTNUM") String AGNTNUM);


    /* Query for when user want to see all the policy details. By directly clicking "Search"*/
    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_PolicyDeatilsOnlyforAgentNumber @AGNTNUM=:AGNTNUM")
    List<Object[]> getPolicyDetailsWithClientName(@Param("AGNTNUM") String AGNTNUM);


    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_PolicyDeatilsForPolicyNo @POLICY_NO=:POLICY_NO")
    List<Object[]> getPolicyColumns(@Param("POLICY_NO") String POLICY_NO);


    @Query(nativeQuery = true,value ="EXEC SalesApp_Select_DuePoliciesForGivenDateRange @agntnum=:agntnum,@startDate=:startDate,@endDate=:endDate,@userType=:userType")
    List<PG_POLICYINFO> findDuePoliciesByAgntnumAndPaidupDateBetween(
            @Param("agntnum") String agntnum,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("userType")String userType
    );

    @Query(nativeQuery = true,value ="EXEC SalesApp_Select_OverduePoliciesForGivenDateRange @agntnum=:agntnum,@startDate=:startDate,@endDate=:endDate")
    List<PG_POLICYINFO> findOverduePoliciesByAgntnumAndPaidupDateBetween(
            @Param("agntnum") String agntnum,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query(nativeQuery = true,value ="EXEC SalesApp_Select_LapsedPoliciesForGivenDateRange @agntnum=:agntnum,@startDate=:startDate,@inputDate=:inputDate")
    List<PG_POLICYINFO> findLapsedPoliciesByAgntnumAndPaidupDateBetween(
            @Param("agntnum") String agntnum,
            @Param("startDate") Date startDate,
            @Param("inputDate") Date inputDate
    );


    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_CountOfDuePolicies @agntnum =:agntnum,@todayDate=:todayDate")
    long countDuePolicies(@Param("agntnum") String agntnum, @Param("todayDate") Date todayDate);

    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_CountOfOverduePolicies @agntnum=:agntnum,@startDate=:startDate,@endDate=:endDate")
    long countOverduePolicies(@Param("agntnum") String agntnum, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_CountOfLapsedPolicies @agntnum=:agntnum,@endDate=:endDate")
    long countLapsedPolicies(@Param("agntnum") String agntnum, @Param("endDate") Date endDate);


    @Query("SELECT COUNT (p) FROM PG_POLICYINFO p WHERE SUBSTRING(p.AGNTNUM, LEN(p.AGNTNUM) - 5, 6)=:agntnum AND PLAN_NAME='PENSION-CRF'")
    long countPensionPolicies (@Param("agntnum")String agntnum );




    // calculate the count for policy_plans.
    @Query("SELECT COUNT (p) FROM PG_POLICYINFO p WHERE SUBSTRING(p.AGNTNUM, LEN(p.AGNTNUM) - 5, 6)=:agntnum AND PLAN_NAME='LIFE-SHILPA'")
    long countLifeShilpaPolicies (@Param("agntnum")String agntnum );

    @Query("SELECT COUNT (p) FROM PG_POLICYINFO p WHERE SUBSTRING(p.AGNTNUM, LEN(p.AGNTNUM) - 5, 6)=:agntnum AND PLAN_NAME='SEYLINC LIFE LONG'")
    long countSeylincLifeLongPolicies (@Param("agntnum")String agntnum );

    @Query("SELECT COUNT (p) FROM PG_POLICYINFO p WHERE p.AGNTNUM=:agntnum AND PLAN_NAME='CEYLINCO UTHUM'")
    long countCeylincoUthumPolicies (@Param("agntnum")String agntnum );

    @Query("SELECT p.PLAN_NAME, COUNT(p.PLAN_NAME) FROM PG_POLICYINFO p WHERE p.AGNTNUM = :agntnum GROUP BY p.PLAN_NAME")
    List<Object[]> findPlanTypesCountByAgntnum(@Param("agntnum") String agntnum);

    default Map<String, Long> countPlanTypesByAgntnum(String agntnum) {
        return findPlanTypesCountByAgntnum(agntnum).stream()
                .collect(Collectors.toMap(
                        entry -> (String) entry[0],
                        entry -> (Long) entry[1]
                ));
    }





}








