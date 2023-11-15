package com.catolica.sc.spaum.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseVo {
    private Long id;

    private String name;

    private String email;

    private List<String> rulesCourses;
}
