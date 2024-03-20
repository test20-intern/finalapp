package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Entity.PG_POLICYLOAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PG_POLICYLOANREPO extends JpaRepository<PG_POLICYLOAN,String> {
    @Query(nativeQuery = true,value = "EXEC SalesApp_Select_PolicyLoanByPolicyNumber @POLICY_NO=:POLICY_NO,@userType=:userType")
    List<PG_POLICYLOAN> getLoanDetailsByPolicyNo(
            @Param("POLICY_NO") String POLICY_NO,
            @Param("userType")String userType
    );
}
