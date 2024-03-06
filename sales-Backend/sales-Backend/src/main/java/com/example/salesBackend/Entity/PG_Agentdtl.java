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
    @Column(name="SoCode")
    private String SoCode;

    @Column(name="Soname")
    private String Soname;

    @Column(name="Branch")
    private String Branch;

    @Column(name="ReportSup")
    private String ReportSup;

    @Column(name="OrgLim")
    private String OrgLim;

    @Column(name="TelNo")
    private String TelNo;

    @Column(name="Nic")
    private String Nic;

}
