package com.example.salesBackend.Repo;


import com.example.salesBackend.Entity.SalesPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SalesPerformanceRepo extends JpaRepository<SalesPerformance,String> {


    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_SalesPerformance @GroupCode=:GroupCode,@BranchCode=:BranchCode,@UnitCode=:UnitCode, @agntnum=:agntnum,@userType=:userType")
    List<Object> getTotalPerformance(
            @Param("GroupCode") String GroupCode,
            @Param("BranchCode") String BranchCode,
            @Param("UnitCode") String UnitCode,
            @Param("agntnum") String agntnum,
            @Param("userType")String userType


    );


}
