package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.SalesAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminLogRepo extends JpaRepository<SalesAppUser,String> {

    @Query(nativeQuery = true, value = "EXEC SalesApp_Select_AdminAccess @userType=:userType, @agntnum=:agntnum")
    String getAdminAccess(

            @Param("agntnum") String agntnum
    );

}
