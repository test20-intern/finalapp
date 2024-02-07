package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLIENT_POLICY")
public class CLIENT_POLICY {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POLICY_NO")
    private PG_POLICYINFO policy;

    @ManyToOne
    @JoinColumn(name = "CLIENT_NO")
    private PG_CLIENTINFO client;

}
