package com.example.salesBackend.Entity;

/* Benefit details table*/
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

    @Column(name = "BENEFIT_CODE")
    private  String BENEFIT_CODE;
    private String DESCRIPTION;
    private String REMARKS;
    private int VIEW_ORDER;

}
