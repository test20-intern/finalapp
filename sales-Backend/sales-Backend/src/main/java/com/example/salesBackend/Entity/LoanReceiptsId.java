package com.example.salesBackend.Entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Embeddable
public class LoanReceiptsId implements Serializable {
    private String POLICY_NO;
    private String RECEIPT_NO;
    private String LOAN_NO;
}
