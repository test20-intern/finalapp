package com.example.salesBackend.Dto.Request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BENEFICIARYREQUEST {



    private String RELATIONSHIP;

    private String NAME;
    private Date DOB;
    private BigDecimal PERCENTAGE;


}
