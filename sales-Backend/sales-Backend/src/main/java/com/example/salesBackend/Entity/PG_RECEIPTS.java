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
@Table(name="PG_RECEIPTS")
public class PG_RECEIPTS {
    @EmbeddedId
    private PG_RECEIPTSId RID;

    @Column(name="RECEIPT_DATE")
    private Date RECEIPT_DATE;


}


