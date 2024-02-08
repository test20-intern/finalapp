package com.example.salesBackend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BENEFITPK implements Serializable {
    private String POLICY_NO;
    private String LIFENO;
    private String BENEFIT_CODE;
    

}
