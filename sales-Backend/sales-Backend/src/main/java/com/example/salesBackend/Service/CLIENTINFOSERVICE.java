package com.example.salesBackend.Service;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Repo.PG_CLIENTINFOREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CLIENTINFOSERVICE {
    @Autowired
    private PG_CLIENTINFOREPO PG_CLIENTINFOREPO;
    public List<PG_CLIENTINFO> getAllClients() {
        return PG_CLIENTINFOREPO.findAll();
    }

}
