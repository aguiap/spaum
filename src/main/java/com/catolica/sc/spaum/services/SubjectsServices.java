package com.catolica.sc.spaum.services;

import com.catolica.sc.spaum.dto.SubjectsDTO;
import com.catolica.sc.spaum.vo.SubjectVo;

import java.util.List;

public interface SubjectsServices {

    List<SubjectsDTO> getAllSubjectsByCourse(Long courseId);

    Long createSubjectByCourseId(SubjectVo subjectVo, Long courseId);
    void createMultipleSubjectByCourseId(List<SubjectVo> subjectsVo, Long courseId);

    void editSubjectByCourseId(SubjectVo subjectVo);

    void deleteSubject(Long subjectId);
}
