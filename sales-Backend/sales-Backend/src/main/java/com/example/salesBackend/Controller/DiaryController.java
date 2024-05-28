package com.example.salesBackend.Controller;


import com.example.salesBackend.Entity.EventsTitle;
import com.example.salesBackend.Service.EventsTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/Diary")
public class DiaryController {

    @Autowired
    private EventsTitleService eventsTitleService;

    // Endpoint to get all events
    @GetMapping("/eventNames")
    public List<EventsTitle> getAllEvents() {
        return eventsTitleService.getAllEvents();
    }

}
