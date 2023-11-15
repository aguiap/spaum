package com.catolica.sc.spaum.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email")
    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<RulesCourse> rulesCourses = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Subjects> subjects = new ArrayList<>();

    public Courses() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses courses = (Courses) o;
        return Objects.equals(id, courses.id) && Objects.equals(name, courses.name) && Objects.equals(rulesCourses, courses.rulesCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rulesCourses);
    }
}
