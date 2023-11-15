package com.catolica.sc.spaum.repositories;

import com.catolica.sc.spaum.model.RulesCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulesCourseRepository extends JpaRepository<RulesCourse, Long> {
}
