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
    private String POLICY_NO;
    @Id
    private String LIFENO;
    @Id
    private String BENEFIT_CODE;

    private BigDecimal COVER_AMOUNT;


    @Data
    public static class PK implements Serializable {
        private String POLICY_NO;
        private String LIFENO;
        private String BENEFIT_CODE;
    }
}
