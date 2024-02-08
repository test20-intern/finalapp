package com.example.salesBackend.Entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PG_BENEFIT_MASTER")
public class PG_BENEFIT_MASTER {

    @Id
//    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name = "BENEFIT_CODE")
    private  String BENEFIT_CODE;

    private String DESCRIPTION;
    private String REMARKS;
    private Long VIEW_ORDER;

}
