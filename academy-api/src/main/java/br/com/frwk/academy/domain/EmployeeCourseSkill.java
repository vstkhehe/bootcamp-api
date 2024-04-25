package br.com.frwk.academy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_employee_course_skill")
@Data
public class EmployeeCourseSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonIgnore
    @ManyToOne
    private EmployeeCourse employeeCourse;

    @NotNull
    private Long skill;

    private Integer exp;

    private boolean active;

    private Integer level;
}
