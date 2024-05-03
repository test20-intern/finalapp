package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.SystemLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface SystemLogsRepo extends JpaRepository<SystemLogs,Long> {
}
