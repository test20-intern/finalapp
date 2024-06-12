package com.example.salesBackend.Service;

import com.example.salesBackend.Repo.AdminLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLogService {
    @Autowired
    private AdminLogRepo adminLogRepo;

    public String getAdminAccess(String UserType, String agntnum) {
        return adminLogRepo.getAdminAccess(UserType, agntnum);
    }
}
