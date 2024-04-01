package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Request.DashboardCounts;
import com.example.salesBackend.Entity.PG_POLICYINFO;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Repo.PG_POLICYINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class POLICYINFOSERVICE {

    @Autowired
    private PG_POLICYINFOREPO pgPolicyInfoRepo;

    public List<Object[]> getPolicyDetailsWithClientName(String AGNTNUM,String userType) {
        try {

            return pgPolicyInfoRepo.getPolicyDetailsWithClientName(AGNTNUM,userType);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving policy details with client name", e);
        }
    }

    public List<Object[]> getPolicyDetailsWithSearchParams(String POLICY_NO, String NIC, String NAME, String CLIENT_NO, String AGNTNUM,String userType) {
        try {
            if (AGNTNUM == null || AGNTNUM.isEmpty()) {
                throw new IllegalArgumentException("AGNTNUM is required");
            }
            return pgPolicyInfoRepo.getPolicyDetailsWithSearchParams(POLICY_NO, NIC, NAME, CLIENT_NO, AGNTNUM,userType);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving policy details with search parameters", e);
        }
    }

    public List<Object[]> getPolicyColumns(String POLICY_NO,String userType) {
        try {
            return pgPolicyInfoRepo.getPolicyColumns(POLICY_NO,userType);
        } catch (Exception e) {

            throw new RuntimeException("Error retrieving policy columns by policy number", e);
        }
    }

// service to get Due policy details.
    public List<PG_POLICYINFO> getDuePolicies(String agntnum, Date inputDate, Date endDate,String userType) {
        return pgPolicyInfoRepo.findDuePoliciesByAgntnumAndPaidupDateBetween(agntnum, inputDate, endDate,userType);
    }

// service to get the overdue policies.
public List<PG_POLICYINFO> getOverduePolicies(String agntnum, Date inputDate, String userType) {
    Date startDate = calculateStartDateForOverdue(inputDate);
    return pgPolicyInfoRepo.findOverduePoliciesByAgntnumAndPaidupDateBetween(agntnum, startDate, inputDate, userType);
}

    private Date calculateStartDateForOverdue(Date inputDate) {
        LocalDate localInputDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate startDateTest = localInputDate.plusDays(2);
        LocalDate startDate = startDateTest.minusMonths(1);// Subtracting 1 month and adding 1 day
        return Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }



    // service to get lapsed policies.
    public List<PG_POLICYINFO> getLapsedPolicies(String agntnum, Date startDate, Date inputDate, String userType) {
        //Date endDate = calculateEndDateForLapsed(inputDate);
        return pgPolicyInfoRepo.findLapsedPoliciesByAgntnumAndPaidupDateBetween(agntnum, startDate,inputDate, userType);
    }

    // here we have to calculate the lapsed policies end date because we have to avoid the overdue period.
    // end date = overdue periods start date.
//    private Date calculateEndDateForLapsed(Date inputDate) {
//        LocalDate localInputDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate endDate = localInputDate.minusMonths(1); // Subtracting a month for the lapsed period
//        return Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//    }


    // to get the number of due/overdue/lapsed policies to show in the dashboard (graphs).
    // we get the current date and calculate the count for each type of policies.

    public DashboardCounts getPolicyCounts(String agntnum,String userType) throws ValueNotExistException {
        LocalDate today = LocalDate.now();
        Date todayDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        LocalDate oneMonthAgo = today.minusMonths(1);
        Date oneMonthAgoDate = Date.from(oneMonthAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        long numberOfDuePolicies = pgPolicyInfoRepo.countDuePolicies(agntnum, todayDate,userType);
        long numberOfOverduePolicies = pgPolicyInfoRepo.countOverduePolicies(agntnum, oneMonthAgoDate, todayDate,userType);
        long numberOfLapsedPolicies = pgPolicyInfoRepo.countLapsedPolicies(agntnum, oneMonthAgoDate,userType);

        if (numberOfDuePolicies == 0 && numberOfOverduePolicies == 0 && numberOfLapsedPolicies == 0) {
            throw new ValueNotExistException("No policies found for agent number: " + agntnum);
        }

        return new DashboardCounts(numberOfDuePolicies, numberOfOverduePolicies, numberOfLapsedPolicies);
    }

    public Map<String, Long> getPlanTypes(String agntnum) {
        return pgPolicyInfoRepo.countPlanTypesByAgntnum(agntnum);
    }







}
