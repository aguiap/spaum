package com.catolica.sc.spaum.controllers;

import com.catolica.sc.spaum.dto.CoursesDTO;
import com.catolica.sc.spaum.services.CoursesServices;
import com.catolica.sc.spaum.vo.CourseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Courses Endpoint")
@RestController
@RequestMapping("/api")
public class CoursesController {

    @Autowired
    CoursesServices coursesServices;

    @Operation(summary = "Returns all courses")
    @GetMapping(value = "/v1/courses/get-all-courses")
    public ResponseEntity<List<CoursesDTO>> getAllCourses(){
        return ResponseEntity.ok(coursesServices.getAllCurses());
    }

    @Operation(summary = "Edit course")
    @PutMapping(value = "/v1/courses")
    public ResponseEntity<?> editCourse(@RequestBody CourseVo courseVo){
        coursesServices.editCourse(courseVo);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Create course")
    @PostMapping(value = "/v1/courses")
    public ResponseEntity<Long> createCourse(@RequestBody CourseVo courseVo){
        return ResponseEntity.ok(coursesServices.createCourse(courseVo));
    }

    @Operation(summary = "Delete course")
    @DeleteMapping(value = "/v1/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId){
        coursesServices.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }
}
