package com.catolica.sc.spaum.repositories;

import com.catolica.sc.spaum.model.TokenAnalysisAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenAnalysisAccessRepository extends JpaRepository<TokenAnalysisAccess, Long> {

    @Query("SELECT t FROM TokenAnalysisAccess t WHERE t.token = :token")
    Optional<TokenAnalysisAccess> findByToken(String token);
}
