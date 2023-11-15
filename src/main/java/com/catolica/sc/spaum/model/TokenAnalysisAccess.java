package com.catolica.sc.spaum.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "token_analysis_access")
@Getter
@Setter
public class TokenAnalysisAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "path")
    private String path;

    @Column(name = "course")
    private String course;

    @Column(name = "type_analyses")
    private String typeAnalyses;

    @Builder.Default
    @OneToMany(mappedBy = "tokenAnalysisAccess", cascade = CascadeType.ALL)
    private List<Analysis> analyses = new ArrayList<>();
}
