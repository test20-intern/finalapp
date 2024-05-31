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
    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_ClientDetailsByPolicyNo @POLICY_NO = :POLICY_NO,@userType=:userType")
    List<PG_CLIENTINFO> getClientDetailsByPolicyNo(
            @Param("POLICY_NO") String POLICY_NO,
            @Param("userType")String userType
            );


// the DOB column in PG_CLIENTINFO is in DECIMAL format in the LIFEDB database. so we convert the DOB to date
// and do the calculation to find clients that have their birthday in a given date range.
// Repository method
@Query(nativeQuery = true, value = "EXEC SalesApp_Select_ClientBirthdaysByAgentNumberAndDateRange " +
        "@startDate = :startDate, " +
        "@endDate = :endDate, " +
        "@GroupCode = :groupCode, " +
        "@BranchCode = :branchCode, " +
        "@UnitCode = :unitCode, " +
        "@agentNumber = :agentNumber, " +
        "@UserType = :userType")
List<Object[]> findClientInfoByAgentAndDateRange(
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("groupCode") String groupCode,
        @Param("branchCode") String branchCode,
        @Param("unitCode") String unitCode,
        @Param("agentNumber") String agentNumber,
        @Param("userType") String userType);


    @Query(nativeQuery = true, value = "EXEC SalesApp_Select_ClientCity  " +
            "@GroupCode = :groupCode, " +
            "@BranchCode = :branchCode, " +
            "@UnitCode = :unitCode, " +
            "@agntnum = :agntnum, " +
            "@City= :City,"+
            "@UserType = :userType")
    List<Object[]> findClientCityByAgentNumber(
            @Param("groupCode") String groupCode,
            @Param("branchCode") String branchCode,
            @Param("unitCode") String unitCode,
            @Param("agntnum") String agntnum,
            @Param("City") String City,
            @Param("userType") String userType);

}










