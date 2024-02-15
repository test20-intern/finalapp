package com.example.salesBackend.Entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
 @Getter
 @Setter
@Embeddable
public class PG_RECEIPTSId implements Serializable {
    private String POLICY_NO;
    private String RECEIPT_NO;
    private BigDecimal AMOUNT;


}
