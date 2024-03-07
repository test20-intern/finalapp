package com.example.salesBackend.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PG_LOANRECEIPTS")
public class PG_LOANRECEIPTS {
    @EmbeddedId
    private LoanReceiptsId LRID;

    @Column(name="RECEIPT_DATE")
    private Date RECEIPT_DATE;

    @Column(name="AMOUNT")
    private BigDecimal AMOUNT;


}
