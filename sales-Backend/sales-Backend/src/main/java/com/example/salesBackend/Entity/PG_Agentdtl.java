package com.example.salesBackend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/* Agent details table*/
@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PG_Agentdtl ")
public class PG_Agentdtl {
    @Id
    @Column(name="SO_CODE")
    private String SO_CODE;

    @Column(name="SONAME")
    private String SONAME;

    @Column(name="BRANCH")
    private String BRANCH;

    @Column(name="REPORT_SUP")
    private String REPORT_SUP;

    @Column(name="ORG_LIM")
    private String ORG_LIM;

    @Column(name="TEL_NO")
    private String TEL_NO;

    @Column(name="NIC")
    private String NIC;

    @Column(name="GROUP_CODE")
    private String GROUP_CODE;

    @Column(name="GROUP_DES")
    private String GROUP_DES;

    @Column(name="UNIT_CODE")
    private String UNIT_CODE;

    @Column(name="SO_CAT")
    private String SO_CAT;

    @Column(name="SO_TYPE")
    private String SO_TYPE;

    @Column(name="DATE_OF_APPOINTMENT")
    private String DATE_OF_APPOINTMENT;


}
