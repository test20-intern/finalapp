package com.example.salesBackend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Embeddable
public class LoanReceiptsId implements Serializable {
    @Column(name="POLICY_NO")
    private String POLICY_NO;

    @Column(name="RECEIPT_NO")
    private String RECEIPT_NO;

    @Column(name="LOAN_NO")
    private String LOAN_NO;
}
