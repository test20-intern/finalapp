package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Response.ClicksCountRecordsDTO;
import com.example.salesBackend.Service.ClicksCountRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("clicks")
public class ClicksCountRecordsController {

    @Autowired
    private ClicksCountRecordsService clicksCountRecordsService;

    @PostMapping("/update")
    public void clicksCountRecordsUpdate (@RequestBody ClicksCountRecordsDTO clicksCountRecordsDTO){
        clicksCountRecordsService.clicksCountRecordsUpdate(clicksCountRecordsDTO);
    }
}
