package com.example.salesBackend.Controller;

import com.example.salesBackend.Service.AdminLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/UserLog")
public class AdminLog {

    @Autowired
    private AdminLogService adminLogService;

    @GetMapping("/adminLogging")
    public ResponseEntity<Boolean> getAdminAccess(

            @RequestParam(required = true) String agntnum
    ) {
        String result = adminLogService.getAdminAccess(agntnum);
        if ("NO ACCESS".equals(result)) {
            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<Boolean>(true , HttpStatus.OK);
        }
    }
}
