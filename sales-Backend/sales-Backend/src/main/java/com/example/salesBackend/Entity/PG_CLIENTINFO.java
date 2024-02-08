package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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

    private String NIC;
    private String NAME;
    private String FULL_NAME;
    private String ADD_1;
    private String ADD_2;
    private String ADD_CITY;
    private String PCODE;
    private String TEL_1;
    private String TEL_2;

//    @OneToMany(mappedBy = "CLIENT_NO")
//    private Set<PG_POLICYINFO> PG_POLICYINFO = new HashSet<>();


}
