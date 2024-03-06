package com.example.salesBackend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Embeddable
public class BeneficiaryId implements Serializable {


    @Column(name = "SOCODE")
    private String SOCODE;

    @Column(name = "CONTRACTID")
    private String CONTRACTID;

    @Column(name = "CUSTOMERID")
    private String CUSTOMERID;

    @Column(name = "RELATIONSHIP")
    private String RELATIONSHIP;

    @Column(name = "CATEGORY")
    private String CATEGORY;
}
