package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.SalesappLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogsRepo extends JpaRepository<SalesappLogs,Long> {
}
