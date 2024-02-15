package com.example.salesBackend.Dto.Request;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class POLICYLOANREQUEST {
    private String POLICY_NO;

    private String LOAN_NO;
    private Date LOAN_DATE;
    private Date LAST_CAPITALIZED_DATE;
    private BigDecimal LOAN_BALANCE;
}
