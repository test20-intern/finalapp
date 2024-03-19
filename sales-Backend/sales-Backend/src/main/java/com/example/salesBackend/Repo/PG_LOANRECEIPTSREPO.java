package com.example.salesBackend.Repo;

import com.example.salesBackend.Dto.Request.LOANRECEIPTREQUEST;
import com.example.salesBackend.Entity.PG_LOANRECEIPTS;
import com.example.salesBackend.Entity.LoanReceiptsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PG_LOANRECEIPTSREPO extends JpaRepository<PG_LOANRECEIPTS, LoanReceiptsId> {
  @Query(nativeQuery = true, value = "EXEC SalesApp_Select_LoanReceiptsForPolicyNo @POLICY_NO=:POLICY_NO,@userType=:userType")
  List<Object[]> findLoanReceiptsDataByPolicyNo(
          @Param("POLICY_NO") String POLICY_NO,
          @Param("userType")String userType);
}
