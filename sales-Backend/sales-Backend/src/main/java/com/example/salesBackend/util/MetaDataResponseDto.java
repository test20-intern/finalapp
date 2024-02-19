package com.example.salesBackend.util;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataResponseDto {

    private Integer code;

    private String message;
}
