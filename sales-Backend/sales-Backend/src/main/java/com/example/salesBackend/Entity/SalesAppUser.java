package com.example.salesBackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAppUser {
    @Id

    private String empId;
    private String name;
    private String status;
    @Column(columnDefinition = "DATETIME")
    private LocalDate createDate;
    private String createUser;
    @Column(columnDefinition = "DATETIME")
    private LocalDate updateDate;
    private String updateUser;


}
