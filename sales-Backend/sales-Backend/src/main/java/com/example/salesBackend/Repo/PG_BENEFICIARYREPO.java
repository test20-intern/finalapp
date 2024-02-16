package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.PG_BENEFICIARY;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PG_BENEFICIARYREPO extends JpaRepository<PG_BENEFICIARY,String> {
}
