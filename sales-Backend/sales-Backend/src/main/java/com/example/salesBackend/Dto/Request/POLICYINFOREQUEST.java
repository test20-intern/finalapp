package com.example.salesBackend.Dto.Request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class POLICYINFOREQUEST {
    private String POLICY_NO;
    private String CLIENT_NO;
//    private String NAME;
    private String PLAN_NAME;
    private String PAYMENT_MODE;
    private BigDecimal SUM_ASSURED;
    private Date RISK_DATE;
    private Date PAIDUP_DATE;
//    private Date PREM_CESS_DATE;
    private BigDecimal TERM;
//    private String BRANCH;
    private BigDecimal PREMIUM;
//    private BigDecimal PREMIUM_DUE;
//    private BigDecimal INTEREST;
    private BigDecimal SUNDRY_BALANCE;
    private BigDecimal TOTAL_DUE;
//    private String POLICY_STATUS;
//    private BigDecimal ACCOUNT_BALANCE;
    private String AGNTNUM;


}
