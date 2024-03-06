package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
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

    @Column(name = "CLIENT_NO")
    private String CLIENT_NO;

    @Column(name = "NAME")
    private String NAME;

    @Column(name = "PLAN_NAME")
    private String PLAN_NAME;

    @Column(name = "PAYMENT_MODE")
    private String PAYMENT_MODE;

    @Column(name = "SUM_ASSURED")
    private BigDecimal SUM_ASSURED;

    @Column(name = "RISK_DATE")
    private Date RISK_DATE;

    @Column(name = "PAIDUP_DATE")
    private Date PAIDUP_DATE;

    @Column(name = "PREM_CESS_DATE")
    private Date PREM_CESS_DATE;

    @Column(name = "TERM")
    private BigDecimal TERM;

    @Column(name = "BRANCH")
    private String BRANCH;

    @Column(name = "PREMIUM")
    private BigDecimal PREMIUM;

    @Column(name = "PREMIUM_DUE")
    private BigDecimal PREMIUM_DUE;

    @Column(name = "INTEREST")
    private BigDecimal INTEREST;

    @Column(name = "SUNDRY_BALANCE")
    private BigDecimal SUNDRY_BALANCE;

    @Column(name = "TOTAL_DUE")
    private BigDecimal TOTAL_DUE;

    @Column(name = "POLICY_STATUS")
    private String POLICY_STATUS;

    @Column(name = "ACCOUNT_BALANCE")
    private BigDecimal ACCOUNT_BALANCE;


    @Column(name = "AGNTNUM")
    private String AGNTNUM;




}
