package com.catolica.sc.spaum.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "rules_courses")
@Getter
@Setter
@NoArgsConstructor
public class RulesCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_courses")
    private Courses course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RulesCourse that = (RulesCourse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, course);
    }

    public RulesCourse(String name, Long courseId){
        this.name = name;
        this.course = new Courses();
        this.course.setId(courseId);
    }

    public RulesCourse(String name, Courses course){
        this.course = course;
        this.name = name;
    }
}
