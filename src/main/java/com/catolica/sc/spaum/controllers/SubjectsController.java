package com.catolica.sc.spaum.controllers;

import com.catolica.sc.spaum.dto.SubjectsDTO;
import com.catolica.sc.spaum.services.SubjectsServices;
import com.catolica.sc.spaum.vo.SubjectVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subjects Endpoint")
@RestController
@RequestMapping("/api")
public class SubjectsController {

    @Autowired
    SubjectsServices subjectsServices;

    @Operation(summary = "Returns all subjects by course")
    @GetMapping(value = "/v1/courses/get-all-subjects/{courseId}")
    public ResponseEntity<List<SubjectsDTO>> getAllSubjectsByCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(subjectsServices.getAllSubjectsByCourse(courseId));
    }


    @Operation(summary = "Creates discipline in the course")
    @PostMapping(value = "/v1/courses/{courseId}/subject")
    public ResponseEntity<Long> createSubjectByCourseId(@RequestBody SubjectVo subjectVo, @PathVariable Long courseId){
        return ResponseEntity.ok(subjectsServices.createSubjectByCourseId(subjectVo, courseId));
    }

    @Operation(summary = "Creates multiple disciplines in the course")
    @PostMapping(value = "/v1/courses/{courseId}/subject/multiple")
    public ResponseEntity<Long> createSubjectByCourseId(@RequestBody List<SubjectVo> subjectsVo, @PathVariable Long courseId){
        subjectsServices.createMultipleSubjectByCourseId(subjectsVo, courseId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Edit discipline in the course")
    @PutMapping(value = "/v1/courses/subject")
    public ResponseEntity<?> editSubjectByCourseId(@RequestBody SubjectVo subjectVo){
        subjectsServices.editSubjectByCourseId(subjectVo);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete discipline in the course")
    @DeleteMapping(value = "/v1/courses/subject/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long subjectId){
        subjectsServices.deleteSubject(subjectId);
        return ResponseEntity.ok().build();
    }
}
