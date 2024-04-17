package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.HierarchyRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.salesBackend.Service.HierarchyService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Hierarchy")
public class HierarchyController {

    @Autowired
    private HierarchyService hierarchyService;

    @GetMapping("/getSOcode")
    public ResponseEntity<List<String>> getSOCode(
            @RequestParam(name = "groupCode") String groupCode,
            @RequestParam(name = "branchCode") String branchCode,
            @RequestParam(name = "unitCode") String unitCode,
            @RequestParam(name = "userType") String userType) {
        HierarchyRequest request = new HierarchyRequest();
        request.setGroupCode(groupCode);
        request.setBranchCode(branchCode);
        request.setUnitCode(unitCode);
        request.setUserType(userType);
        List<String> soCodes = hierarchyService.getSOCode(request);
        return ResponseEntity.ok().body(soCodes);
    }

}
