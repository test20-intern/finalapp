package com.example.salesBackend.Dto.Request;


import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LOANRECEIPTREQUEST {

    private String POLICY_NO;

    private String RECEIPT_NO;

    private String LOAN_NO;
    private Date RECEIPT_DATE;
    private BigDecimal AMOUNT;
}
