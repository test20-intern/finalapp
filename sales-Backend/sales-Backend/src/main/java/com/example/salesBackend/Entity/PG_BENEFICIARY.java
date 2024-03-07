package com.example.salesBackend.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class PG_BENEFICIARY {

    @EmbeddedId
    private BeneficiaryId BID;
    @Column(name="NAME")
    private String NAME;

    @Column(name="DOB")
    private Date DOB;

    @Column(name="PERCENTAGE")
    private BigDecimal PERCENTAGE;

    @Column(name="LASTUPDATED")
    private Date LASTUPDATED;


}
