package com.example.salesBackend.Service;

import com.example.salesBackend.Entity.PG_POLICYINFO;
import com.example.salesBackend.Repo.PG_POLICYINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class POLICYINFOSERVICE {

    @Autowired
    private PG_POLICYINFOREPO pgPolicyInfoRepo;


    public List<Object[]> getPolicyDetailsWithClientName() {
        try {
            return pgPolicyInfoRepo.getPolicyDetailsWithClientName();
        } catch (Exception e) {
            // Handle exceptions here
            throw new RuntimeException("Error retrieving policy details with client name", e);
        }
    }

    public List<Object[]> getPolicyDetailsWithSearchParams(String POLICY_NO, String NIC, String NAME, String CLIENT_NO, String AGNTNUM) {
        try {
            return pgPolicyInfoRepo.getPolicyDetailsWithSearchParams(POLICY_NO, NIC, NAME, CLIENT_NO, AGNTNUM);
        } catch (Exception e) {
            // Handle exceptions here
            throw new RuntimeException("Error retrieving policy details with search parameters", e);
        }
    }



    public List<Object[]> getPolicyColumns(String POLICY_NO) {
        try {
            return pgPolicyInfoRepo.getPolicyColumns(POLICY_NO);
        } catch (Exception e) {
            // Handle exceptions here
            throw new RuntimeException("Error retrieving policy columns by policy number", e);
        }
    }



    public List<PG_POLICYINFO> getDuePolicies(String agntnum, Date inputDate) {
        Date endDate = calculateEndDate(inputDate);
        return pgPolicyInfoRepo.findDuePoliciesByAgntnumAndPaidupDateBetween(agntnum, inputDate, endDate);
    }

    private Date calculateEndDate(Date inputDate) {
        LocalDate localInputDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = ((LocalDate) localInputDate).plusMonths(1);
        return Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public List<PG_POLICYINFO> getOverduePolicies(String agntnum, Date inputDate) {
        Date startDate = calculateStartDateForOverdue(inputDate);
        return pgPolicyInfoRepo.findOverduePoliciesByAgntnumAndPaidupDateBetween(agntnum, startDate, inputDate);
    }

    public List<PG_POLICYINFO> getLapsedPolicies(String agntnum, Date inputDate) {
        Date startDate = calculateStartDateForLapsed(inputDate);
        return pgPolicyInfoRepo.findLapsedPoliciesByAgntnumAndPaidupDateBetween(agntnum, startDate, inputDate);
    }

    private Date calculateStartDateForOverdue(Date inputDate) {
        LocalDate localInputDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate startDate = localInputDate.minusMonths(1); // Subtracting 1 month
        return Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Date calculateStartDateForLapsed(Date inputDate) {
        LocalDate localInputDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate startDate = localInputDate.minusYears(1); // Assuming a 1-year lapsed period
        return Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
