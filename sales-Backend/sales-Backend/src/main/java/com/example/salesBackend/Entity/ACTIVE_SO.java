package com.example.salesBackend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACTIVE_SO")
public class ACTIVE_SO {


    @Column(name = "GROUP_CODE")
    private int GROUP_CODE;

    @Column(name = "GROUP_DES")
    private String GROUP_DES;

    @Column(name = "SUB_OFFICE_CODE")
    private String SUB_OFFICE_CODE;

    @Column(name = "UNIT_CODE")
    private String UNIT_CODE;


    @Id
    @Column(name = "SO_CODE")
    private int SO_CODE;

    @Column(name = "NAME_ADD")
    private String NAME_ADD;

    @Column(name = "DATE_OF_APPOINTMENT")
    private Date DATE_OF_APPOINTMENT;

    @Column(name = "SO_CAT")
    private String SO_CAT;

    @Column(name = "SO_TYPE")
    private String SO_TYPE;


}
