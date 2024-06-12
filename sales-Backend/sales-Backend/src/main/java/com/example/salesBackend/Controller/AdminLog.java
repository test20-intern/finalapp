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
    public ResponseEntity<String> getAdminAccess(
            @RequestParam(required = true) String UserType,
            @RequestParam(required = true) String agntnum
    ) {
        String result = adminLogService.getAdminAccess(UserType, agntnum);
        if ("NO ACCESS".equals(result)) {
            return new ResponseEntity<>("NO ACCESS", HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>("GIVE ACCESS" , HttpStatus.OK);
        }
    }
}
