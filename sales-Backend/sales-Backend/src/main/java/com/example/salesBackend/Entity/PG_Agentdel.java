package com.example.salesBackend.Entity;

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
@Table(name="PG_Agentdel ")
public class PG_Agentdel {
    @Id
    private String SoCode;
    private String Soname;
    private String Branch;
    private String ReportSup;
    private String OrgLim;
    private String TelNo;
    private String Nic;

}
