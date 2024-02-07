package com.example.salesBackend.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PG_LOANRECEIPTS")
public class PG_LOANRECEIPTS {
    @Id
//    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "POLICY_NO")
    private String POLICY_NO;

    private String RECEIPT_NO;
    private String LOAN_NO;
    private Date RECEIPT_DATE;
    private Long AMOUNT;


}
