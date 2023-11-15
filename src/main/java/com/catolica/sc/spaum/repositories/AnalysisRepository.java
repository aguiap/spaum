package com.catolica.sc.spaum.repositories;

import com.catolica.sc.spaum.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

    @Query("SELECT a FROM Analysis a WHERE a.tokenAnalysisAccess.id = :id")
    List<Analysis> findByIdToken(Long id);

}
