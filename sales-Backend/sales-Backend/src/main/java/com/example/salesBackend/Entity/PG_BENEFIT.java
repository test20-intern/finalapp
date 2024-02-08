package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PG_BENEFIT")
@IdClass(BENEFITPK.class)
public class PG_BENEFIT {

    @Id
    private String POLICY_NO;
    @Id
    private String LIFENO;
    @Id
    private String BENEFIT_CODE;

    private BigDecimal COVER_AMOUNT;


}
