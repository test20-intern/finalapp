package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
/* Benefits details  table*/
@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PG_BENEFIT")
@IdClass(PG_BENEFIT.PK.class)
public class PG_BENEFIT {

    @Id
    @Column(name="POLICY_NO")
    private String POLICY_NO;
    @Id
    @Column(name="LIFENO")
    private String LIFENO;
    @Id
    @Column(name="BENEFIT_CODE")
    private String BENEFIT_CODE;

    @Column(name="COVER_AMOUNT")
    private BigDecimal COVER_AMOUNT;


    @Data
    public static class PK implements Serializable {
        private String POLICY_NO;
        private String LIFENO;
        private String BENEFIT_CODE;
    }
}
