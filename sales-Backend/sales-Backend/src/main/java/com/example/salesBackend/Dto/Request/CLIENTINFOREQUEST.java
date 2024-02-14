package com.example.salesBackend.Dto.Request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CLIENTINFOREQUEST {

    private String CLIENT_NO;

    private String NIC;
//    private String NAME;
    private String FULL_NAME;
    private String ADD_1;
    private String ADD_2;
    private String ADD_CITY;
    private String PCODE;
    private String TEL_1;
    private String TEL_2;
}
