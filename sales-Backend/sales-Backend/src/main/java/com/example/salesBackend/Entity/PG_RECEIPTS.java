package com.example.salesBackend.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PG_RECEIPTS")
public class PG_RECEIPTS {
    @Id
//    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "POLICY_NO")
    private String POLICY_NO;

    private String RECEIPT_NO;
    private Date RECEIPT_DATE;
    private Long AMOUNT;
}
