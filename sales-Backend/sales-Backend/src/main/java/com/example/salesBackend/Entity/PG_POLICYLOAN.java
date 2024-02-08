package com.example.salesBackend.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PG_POLICYLOAN")
public class PG_POLICYLOAN {
    @Id

    @Column(name = "POLICY_NO")
    private String POLICY_NO;

    private String LOAN_NO;
    private Date LOAN_DATE;
    private Date LAST_CAPITALIZED_DATE;
    private BigDecimal LOAN_BALANCE;
}
