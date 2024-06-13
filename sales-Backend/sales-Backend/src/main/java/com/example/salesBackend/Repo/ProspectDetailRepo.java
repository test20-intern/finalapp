package com.example.salesBackend.Repo;

import com.example.salesBackend.Entity.salesappprospectdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProspectDetailRepo extends JpaRepository<salesappprospectdetail,String> {

    @Query(nativeQuery = true, value = "EXEC SalesApp_Select_SuspectList  " +
            "@agntnum = :agntnum " )
    List<Object[]> findSuspectsByAgentNumberForDiary(
            @Param("agntnum") String agntnum
           );
}
