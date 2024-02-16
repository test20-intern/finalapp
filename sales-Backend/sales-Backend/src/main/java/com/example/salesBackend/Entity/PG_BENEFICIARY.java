package com.example.salesBackend.Entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PG_BENEFICIARY")
public class PG_BENEFICIARY {

    @EmbeddedId
    private BeneficiaryId BID;
    private String NAME;
    private Date DOB;
    private BigDecimal PERCENTAGE;
    private Date LASTUPDATED;

}
