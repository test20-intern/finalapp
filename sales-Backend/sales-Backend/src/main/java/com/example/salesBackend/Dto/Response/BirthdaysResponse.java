package com.example.salesBackend.Dto.Response;

import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import lombok.*;




@NoArgsConstructor
@Data
// BirthdaysResponse.java

public class BirthdaysResponse {

    private PG_BENEFICIARY beneficiary;
    private PG_CLIENTINFO clientInfo;

    public BirthdaysResponse(PG_BENEFICIARY beneficiary, PG_CLIENTINFO clientInfo) {
        this.beneficiary = beneficiary;
        this.clientInfo = clientInfo;
    }

    // Getters and setters for beneficiary and clientInfo

    public PG_BENEFICIARY getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(PG_BENEFICIARY beneficiary) {
        this.beneficiary = beneficiary;
    }

    public PG_CLIENTINFO getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(PG_CLIENTINFO clientInfo) {
        this.clientInfo = clientInfo;
    }
}

