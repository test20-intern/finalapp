package com.example.salesBackend.Controller;

import com.example.salesBackend.Service.HierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/Hierarchy")
public class HierarchyController {

    @Autowired
    private HierarchyService hierarchyService;

    @PostMapping("/getSOcode")
    public ResponseEntity<List<String>> getSOCode(@RequestBody com.example.salesBackend.Dto.Request.HierarchyRequest request) {
        List<String> soCodes = hierarchyService.getSOCode(request);
        return ResponseEntity.ok().body(soCodes);
    }
}
