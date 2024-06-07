package com.example.salesBackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalAmountsForEachDay {

    private int id;
    private Date receiptDate;
    private BigDecimal totalAmount;






}
