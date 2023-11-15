package com.catolica.sc.spaum.model;

import com.catolica.sc.spaum.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "analysis")
@Getter
@Setter
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "registration")
    private String registration;

    @Column(name = "note_one")
    private Double noteOne;

    @Column(name = "note_two")
    private Double noteTwo;

    @Column(name = "note_three")
    private Double noteThree;

    @Column(name = "note_subs")
    private Double noteSubs;

    @Column(name = "total_fouls")
    private Integer totalFouls;

    @Column(name = "status_notes")
    private Status statusNotes;

    @Column(name = "status_fouls")
    private Status statusFouls;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_token_analysis_access")
    private TokenAnalysisAccess tokenAnalysisAccess;
}
