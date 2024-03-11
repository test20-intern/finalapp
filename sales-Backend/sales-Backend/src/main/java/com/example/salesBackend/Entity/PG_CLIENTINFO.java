package com.example.salesBackend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PG_CLIENTINFO")
public class PG_CLIENTINFO {
    @Id
    @Column(name = "CLIENT_NO")
    private String CLIENT_NO;

    @Column(name="NIC")
    private String NIC;

    @Column(name="NAME")
    private String NAME;

    @Column(name="FULL_NAME")
    private String FULL_NAME;

    @Column(name="ADD_1")
    private String ADD_1;

    @Column(name="ADD_2")
    private String ADD_2;

    @Column(name="ADD_CITY")
    private String ADD_CITY;

    @Column(name="PCODE")
    private String PCODE;

    @Column(name="TEL_1")
    private String TEL_1;

    @Column(name="TEL_2")
    private String TEL_2;

    @Column(name = "DOB")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private BigDecimal DOB;


    public void setId(int i) {
    }
}
