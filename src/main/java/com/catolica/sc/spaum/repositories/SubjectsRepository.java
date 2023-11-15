package com.catolica.sc.spaum.repositories;

import com.catolica.sc.spaum.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

    @Query("SELECT s FROM Subjects s WHERE s.course.id = :id")
    List<Subjects> findByCourseId(Long id);
}
