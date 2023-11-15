package com.catolica.sc.spaum.services;

import com.catolica.sc.spaum.dto.CoursesDTO;
import com.catolica.sc.spaum.vo.CourseVo;

import java.util.List;

public interface CoursesServices {
    List<CoursesDTO> getAllCurses();

    void editCourse(CourseVo courseVo);

    void deleteCourse(Long courseId);

    Long createCourse(CourseVo courseVo);
}
