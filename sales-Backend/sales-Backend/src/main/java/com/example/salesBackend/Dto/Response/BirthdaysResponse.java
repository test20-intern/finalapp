package com.example.salesBackend.Dto.Response;

import com.example.salesBackend.Entity.BeneficiaryId;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import lombok.*;


@NoArgsConstructor
@Data
@Getter
@Setter
public class BirthdaysResponse {
    private BeneficiaryBday beneficiary;
    private BeneficiaryClientBday clientInfo;
    private String customerId;
    private String client_No;
    private String relationship;
    private String agentNum;

    public BirthdaysResponse(PG_BENEFICIARY beneficiaryEntity, PG_CLIENTINFO clientInfoEntity, String agentNum) {
        this.beneficiary = convertToBeneficiaryDTO(beneficiaryEntity);
        this.clientInfo = convertToClientInfoDTO(clientInfoEntity);
        this.customerId = beneficiaryEntity.getBID().getCUSTOMERID();
        this.client_No = clientInfoEntity.getCLIENT_NO();
        this.relationship = beneficiaryEntity.getBID().getRELATIONSHIP();
        this.agentNum = agentNum;
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
