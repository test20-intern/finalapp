package com.example.salesBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PG_AgentDel ")
public class PG_AgentDel {
    @Id
    private String SoCode;
    private String Soname;
    private String Branch;
    private String ReportSup;
    private String OrgLim;
    private String TelNo;
    private String Nic;
}
