package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PG_POLICYINFO")
public class PG_POLICYINFO {
    @Id
    @Column(name = "POLICY_NO")
    private String POLICY_NO;

    private String CLIENT_NO;
    private String NAME;
    private String PLAN_NAME;
    private String PAYMENT_MODE;
    private Long SUM_ASSURED;
    private Date RISK_DATE;
    private Date PAIDUP_DATE;
    private Date PREM_CESS_DATE;
    private Long TERM;
    private String BRANCH;
    private Long PREMIUM;
    private Long PREMIUM_DUE;
    private Long INTEREST;
    private Long SUNDRY_BALANCE;
    private Long TOTAL_DUE;
    private String POLICY_STATUS;
    private Long ACCOUNT_BALANCE;

    @ManyToOne
    @MapsId("CLIENT_NO")
    @JoinColumn(name = "CLIENT_NO")
    private PG_POLICYINFO PG_POLICYINFO;
}
