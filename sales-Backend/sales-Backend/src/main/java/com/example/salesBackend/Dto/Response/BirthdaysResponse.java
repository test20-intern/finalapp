package com.example.salesBackend.Dto.Response;

import com.example.salesBackend.Entity.BeneficiaryId;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import lombok.*;




@NoArgsConstructor
@Data
// BirthdaysResponse.java

public class BirthdaysResponse {

    private BeneficiaryBday beneficiary;
    private BeneficiaryClientBday clientInfo;
    private String customerId;
    private String client_No;

    public BirthdaysResponse(PG_BENEFICIARY beneficiaryEntity, PG_CLIENTINFO clientInfoEntity) {
        this.beneficiary = convertToBeneficiaryDTO(beneficiaryEntity);
        this.clientInfo = convertToClientInfoDTO(clientInfoEntity);
        this.customerId = beneficiaryEntity.getBID().getCUSTOMERID();
        this.client_No = clientInfoEntity.getCLIENT_NO();
    }

    // Conversion methods
    private BeneficiaryBday convertToBeneficiaryDTO(PG_BENEFICIARY beneficiaryEntity) {
        BeneficiaryBday dto = new BeneficiaryBday();
        dto.setNAME(beneficiaryEntity.getNAME());
        dto.setDOB(beneficiaryEntity.getDOB());
        return dto;
    }

    private BeneficiaryClientBday convertToClientInfoDTO(PG_CLIENTINFO clientInfoEntity) {
        BeneficiaryClientBday dto = new BeneficiaryClientBday();
        dto.setADD_CITY(clientInfoEntity.getADD_CITY());
        dto.setTEL_1(clientInfoEntity.getTEL_1());
        dto.setTEL_2(clientInfoEntity.getTEL_2());
        return dto;
    }



}

