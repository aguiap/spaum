package com.catolica.sc.spaum.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentVo {
    private String name;

    private List<StudentSubjectVo> value;
}
