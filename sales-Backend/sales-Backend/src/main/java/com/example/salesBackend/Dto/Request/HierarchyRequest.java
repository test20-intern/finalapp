package com.example.salesBackend.Dto.Request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class HierarchyRequest {

    private String groupCode;
    private String branchCode;
    private String unitCode;
    private String userType;


}
