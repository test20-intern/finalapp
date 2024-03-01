package com.example.salesBackend.Dto.Response;

import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BirthdaysResponse {
    private PG_CLIENTINFO clientInfo;
    private PG_BENEFICIARY beneficiary;
}
