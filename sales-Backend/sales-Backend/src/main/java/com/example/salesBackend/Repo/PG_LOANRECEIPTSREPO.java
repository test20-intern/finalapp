package com.example.salesBackend.Repo;

import com.example.salesBackend.Dto.Request.LOANRECEIPTREQUEST;
import com.example.salesBackend.Entity.PG_LOANRECEIPTS;
import com.example.salesBackend.Entity.LoanReceiptsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PG_LOANRECEIPTSREPO extends JpaRepository<PG_LOANRECEIPTS, LoanReceiptsId> {
    @Query("SELECT new com.example.salesBackend.Dto.Request.LOANRECEIPTREQUEST(" +
            "lr.LRID.POLICY_NO, lr.LRID.RECEIPT_NO, lr.LRID.LOAN_NO, lr.RECEIPT_DATE, lr.AMOUNT) " +
            "FROM PG_LOANRECEIPTS lr " +
            "WHERE lr.LRID.POLICY_NO = :POLICY_NO " +
            "ORDER BY lr.RECEIPT_DATE DESC")
    List<LOANRECEIPTREQUEST> findLoanReceiptByPolicyNo(String POLICY_NO);
}
