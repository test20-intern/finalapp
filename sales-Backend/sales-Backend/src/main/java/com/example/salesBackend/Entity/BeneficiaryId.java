package com.example.salesBackend.Entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Embeddable
public class BeneficiaryId implements Serializable {


    private String SOCODE;
    private String CONTRACTID;
    private String CUSTOMERID;
    private String RELATIONSHIP;
    private String CATEGORY;
}
