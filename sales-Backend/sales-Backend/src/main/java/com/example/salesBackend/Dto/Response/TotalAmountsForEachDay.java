package com.example.salesBackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor

public class TotalAmountsForEachDay {

    private static int counter = 1;
    private int  id;
    private Date receiptDate;
    private BigDecimal totalAmount;


    public TotalAmountsForEachDay() {
        this.id = counter++;

    }



}
