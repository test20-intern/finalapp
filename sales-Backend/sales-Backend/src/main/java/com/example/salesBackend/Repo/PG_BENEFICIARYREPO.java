package com.example.salesBackend.Repo;

import com.example.salesBackend.Dto.Response.BirthdaysResponse;
import com.example.salesBackend.Entity.BeneficiaryId;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PG_BENEFICIARYREPO extends JpaRepository<PG_BENEFICIARY, BeneficiaryId> {

    @Query("SELECT pb FROM PG_BENEFICIARY pb JOIN PG_POLICYINFO pp ON pb.BID.CUSTOMERID = pp.CLIENT_NO WHERE pp.POLICY_NO = :policyNo")
    List<PG_BENEFICIARY> findByPolicyNo(@Param("policyNo") String policyNo);

    @Query("SELECT new com.example.salesBackend.Dto.Response.BirthdaysResponse(b, c) FROM PG_BENEFICIARY b " +
            "JOIN PG_CLIENTINFO c ON b.BID.CUSTOMERID = c.CLIENT_NO " +
            "JOIN PG_POLICYINFO p ON c.CLIENT_NO = p.CLIENT_NO " +
            "WHERE SUBSTRING(p.AGNTNUM, LEN(p.AGNTNUM) - 5, 6) = :agntnum " +
            "AND FUNCTION('MONTH', b.DOB) * 100 + FUNCTION('DAY', b.DOB) >= FUNCTION('MONTH', :startDate) * 100 + FUNCTION('DAY', :startDate) " +
            "AND FUNCTION('MONTH', b.DOB) * 100 + FUNCTION('DAY', b.DOB) <= FUNCTION('MONTH', :endDate) * 100 + FUNCTION('DAY', :endDate) " +
            "ORDER BY FUNCTION('MONTH', b.DOB) * 100 + FUNCTION('DAY', b.DOB) ASC"
    )
    List<BirthdaysResponse> findBirthdaysByAgentNumber(
            @Param("agntnum") String agntnum,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );




}
