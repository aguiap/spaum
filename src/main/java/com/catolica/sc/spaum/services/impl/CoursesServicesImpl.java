package com.catolica.sc.spaum.services.impl;

import com.catolica.sc.spaum.dto.CoursesDTO;
import com.catolica.sc.spaum.exceptions.ExceptionHelper;
import com.catolica.sc.spaum.model.Courses;
import com.catolica.sc.spaum.model.RulesCourse;
import com.catolica.sc.spaum.repositories.CoursesRepository;
import com.catolica.sc.spaum.repositories.RulesCourseRepository;
import com.catolica.sc.spaum.services.CoursesServices;
import com.catolica.sc.spaum.utils.CollectionsUtils;
import com.catolica.sc.spaum.vo.CourseVo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoursesServicesImpl implements CoursesServices {
    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    RulesCourseRepository rulesCourseRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CoursesDTO> getAllCurses(){
        List<Courses> courses = coursesRepository.findAllByNameAsc();
        return courses.stream()
                .map(c -> mapper.map(c, CoursesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void editCourse(CourseVo courseVo){
        Optional<Courses> optionalCourse = coursesRepository.findByName(courseVo.getName());
        if(optionalCourse.isPresent() && !Objects.equals(optionalCourse.get().getId(), courseVo.getId()))
            throw ExceptionHelper.duplicateCourse();
        Optional<Courses> course = coursesRepository.findById(courseVo.getId());
        course.ifPresent(courseGet -> {
            courseGet.setName(courseVo.getName());
            courseGet.setEmail(courseVo.getEmail());
            rulesCourseRepository.deleteAll(courseGet.getRulesCourses());
            if(CollectionsUtils.isNotEmpty(courseVo.getRulesCourses())){
                courseGet.setRulesCourses(courseVo.getRulesCourses()
                        .stream().map((r) -> new RulesCourse(r, courseGet.getId()))
                        .collect(Collectors.toList()));
            } else {
                courseGet.setRulesCourses(new ArrayList<>());
            }
            coursesRepository.saveAndFlush(courseGet);
        });
    }

    @Override
    public Long createCourse(CourseVo courseVo){
        Optional<Courses> optionalCourse = coursesRepository.findByName(courseVo.getName());
        if(optionalCourse.isPresent())
            throw ExceptionHelper.duplicateCourse();
        Courses createCourse = new Courses();
        createCourse.setName(courseVo.getName());
        createCourse.setEmail(courseVo.getEmail());
        if(CollectionsUtils.isNotEmpty(courseVo.getRulesCourses())){
            createCourse.setRulesCourses(courseVo.getRulesCourses()
                    .stream().map((r) -> new RulesCourse(r, createCourse))
                    .collect(Collectors.toList()));
        }
        Courses save = coursesRepository.save(createCourse);
        return save.getId();
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId){
        coursesRepository.deleteById(courseId);
    }
}
