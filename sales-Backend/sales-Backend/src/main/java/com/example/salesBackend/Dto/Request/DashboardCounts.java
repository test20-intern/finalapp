package com.example.salesBackend.Dto.Request;// DashboardCounts.java

import lombok.*;

@Setter
@Getter

@Data

public class DashboardCounts {

    private long numberOfDuePolicies;
    private long numberOfOverduePolicies;
    private long numberOfLapsedPolicies;

    public DashboardCounts() {
        // Default constructor
    }

    public DashboardCounts(long numberOfDuePolicies, long numberOfOverduePolicies, long numberOfLapsedPolicies) {
        this.numberOfDuePolicies = numberOfDuePolicies;
        this.numberOfOverduePolicies = numberOfOverduePolicies;
        this.numberOfLapsedPolicies = numberOfLapsedPolicies;
    }


}
