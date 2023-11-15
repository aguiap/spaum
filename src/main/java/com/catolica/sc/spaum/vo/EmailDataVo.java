package com.catolica.sc.spaum.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailDataVo {
    private String forEmail;

    private String path;

    private String course;

    private String typeAnalyses;

    private List<StudentVo> dataProcessing;
}
