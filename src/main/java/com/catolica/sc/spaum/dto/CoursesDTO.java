package com.catolica.sc.spaum.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoursesDTO {
    private Long id;

    private String name;

    private String email;

    private List<RulesCourseDTO> rulesCourses;
}
