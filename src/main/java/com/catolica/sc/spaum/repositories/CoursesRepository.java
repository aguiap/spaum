package com.catolica.sc.spaum.repositories;

import com.catolica.sc.spaum.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    @Query("SELECT c FROM Courses c JOIN c.rulesCourses r ORDER BY c.name ASC")
    List<Courses> findAllByNameAsc();

    @Query("SELECT c FROM Courses c JOIN c.rulesCourses r WHERE c.name = :name")
    Optional<Courses> findByName(String name);
}
