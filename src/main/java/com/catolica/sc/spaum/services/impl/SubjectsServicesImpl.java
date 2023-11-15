package com.catolica.sc.spaum.services.impl;

import com.catolica.sc.spaum.dto.SubjectsDTO;
import com.catolica.sc.spaum.model.Courses;
import com.catolica.sc.spaum.model.Subjects;
import com.catolica.sc.spaum.repositories.CoursesRepository;
import com.catolica.sc.spaum.repositories.SubjectsRepository;
import com.catolica.sc.spaum.services.SubjectsServices;
import com.catolica.sc.spaum.vo.SubjectVo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectsServicesImpl implements SubjectsServices {

    @Autowired
    SubjectsRepository subjectsRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<SubjectsDTO> getAllSubjectsByCourse(Long courseId) {
        List<Subjects> subjects = subjectsRepository.findByCourseId(courseId);
        return subjects.stream()
                .map(s -> mapper.map(s, SubjectsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long createSubjectByCourseId(SubjectVo subjectVo, Long courseId) {
        Optional<Courses> byId = coursesRepository.findById(courseId);
        if(byId.isPresent()) {
            Subjects createSubjects = new Subjects();
            createSubjects.setName(subjectVo.getName());
            createSubjects.setHours(subjectVo.getHours());
            createSubjects.setCourse(byId.get());
            Subjects save = subjectsRepository.save(createSubjects);
            return save.getId();
        }
        return null;
    }

    @Override
    public void createMultipleSubjectByCourseId(List<SubjectVo> subjectsVo, Long courseId) {
        for (SubjectVo subjectVo: subjectsVo) {
            createSubjectByCourseId(subjectVo, courseId);
        }
    }

    @Override
    public void editSubjectByCourseId(SubjectVo subjectVo) {
        Optional<Subjects> byId = subjectsRepository.findById(subjectVo.getId());
        if(byId.isPresent()) {
            byId.get().setName(subjectVo.getName());
            byId.get().setHours(subjectVo.getHours());
            subjectsRepository.save(byId.get());
        }
    }

    @Override
    public void deleteSubject(Long subjectId) {
        Optional<Subjects> byId = subjectsRepository.findById(subjectId);
        byId.ifPresent(subjects -> subjectsRepository.delete(subjects));
    }
}
