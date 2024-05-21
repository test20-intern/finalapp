//package com.example.salesBackend.Controller;
//
//import com.example.salesBackend.Dto.Response.ClientCityDTO;
//import com.example.salesBackend.Service.CLIENTINFOSERVICE;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@RestController
//@CrossOrigin
//@RequestMapping("/api/v1/diaryDetails")
//public class DiaryController {
//
//    @Autowired
//    private CLIENTINFOSERVICE clientInfoService;
//    @GetMapping("/ClientCity")
//    public List<ClientCityDTO> getClientCity(
//            @RequestParam String groupCode,
//            @RequestParam String branchCode,
//            @RequestParam String unitCode,
//            @RequestParam String agntnum,
//            @RequestParam String userType) {
//        return clientInfoService.getClientCity(groupCode, branchCode, unitCode, agntnum, userType);
//    }
//}
