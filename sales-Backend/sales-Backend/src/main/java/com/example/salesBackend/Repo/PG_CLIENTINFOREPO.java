package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.BENEFITPK;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PG_CLIENTINFOREPO extends JpaRepository<PG_CLIENTINFO, String> {

}
