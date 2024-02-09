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
    private String CLIENT_NO;
    private String NAME;
    private String PLAN_NAME;
    private String PAYMENT_MODE;
    private BigDecimal SUM_ASSURED;
    private Date RISK_DATE;
    private Date PAIDUP_DATE;
    private Date PREM_CESS_DATE;
    private BigDecimal TERM;
    private String BRANCH;
    private BigDecimal PREMIUM;
    private BigDecimal PREMIUM_DUE;
    private BigDecimal INTEREST;
    private BigDecimal SUNDRY_BALANCE;
    private BigDecimal TOTAL_DUE;
    private String POLICY_STATUS;
    private BigDecimal ACCOUNT_BALANCE;

    @Transient
    private Long temporaryId; /*pass the data with a temporary id.(frontend can't retrive data without id
     when using MUI data grid.*/


}
