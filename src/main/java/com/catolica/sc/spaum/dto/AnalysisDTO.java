package com.catolica.sc.spaum.dto;

import com.catolica.sc.spaum.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysisDTO {
    private Long id;

    private String name;

    private String subject;

    private String registration;

    private Double noteOne;

    private Double noteTwo;

    private Double noteThree;

    private Double noteSubs;

    private Integer totalFouls;

    private Status statusNotes;

    private Status statusFouls;
}
