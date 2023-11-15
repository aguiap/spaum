package com.catolica.sc.spaum.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class TokenAnalysisAccessDTO {
    private Long id;

    private String token;

    private String path;

    private String course;

    private String typeAnalyses;

    @Builder.Default
    private List<AnalysisDTO> analyses = new ArrayList<>();
}
