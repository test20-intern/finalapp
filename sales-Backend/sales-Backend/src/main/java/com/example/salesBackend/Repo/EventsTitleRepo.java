package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.EventsTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsTitleRepo extends JpaRepository<EventsTitle, Long> {

    List<EventsTitle> findByStatus(int status);
}
