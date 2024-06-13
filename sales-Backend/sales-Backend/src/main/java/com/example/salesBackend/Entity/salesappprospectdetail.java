package com.example.salesBackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salesappprospectdetail")
public class salesappprospectdetail {
    @Id
    private String SOCode;
    private String Title;
    private String initials;
    private String Surname;
    private String MobileNo;
    private String  HomeNo;
    private String Add_1;
    private String Add_2;
    private String Add_City;
    private Date DOB;
    private String NIC;
    private String Marital_Status;
    private String Occupation;
    private Date LastUpdated;
    private Date enteredDate;
    private String lastUpdateUser;
    private String enteredUser;

}
