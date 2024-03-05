package com.example.salesBackend.Dto.Response;

import com.example.salesBackend.Entity.PG_POLICYINFO;
import com.example.salesBackend.Entity.PG_RECEIPTSId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter

public class AgentReceipts {

    private Date receiptDate;
    private PG_RECEIPTSId rid;
    private String name;

    public AgentReceipts(Date receiptDate, PG_RECEIPTSId rid, String name) {
        this.receiptDate = receiptDate;
        this.rid = rid;
        this.name = name;
    }
}
