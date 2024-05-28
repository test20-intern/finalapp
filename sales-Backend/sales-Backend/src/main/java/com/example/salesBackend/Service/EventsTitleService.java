package com.example.salesBackend.Service;

import com.example.salesBackend.Entity.EventsTitle;
import com.example.salesBackend.Repo.EventsTitleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventsTitleService {
    @Autowired
    private EventsTitleRepo eventsTitleRepo;

    // Method to get all events
    public List<EventsTitle> getAllEvents() {
        return eventsTitleRepo.findAll();
    }
}
