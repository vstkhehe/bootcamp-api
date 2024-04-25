package br.com.frwk.academy.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee_course_tb")
@Data
public class EmployeeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idEmployee;
    private Long idCourse;
    private Boolean active;
    private LocalDate dtStarted;
    private LocalDate dtFinished;

    @OneToMany(mappedBy = "employeeCourse")
    List<EmployeeCourseLesson> lsEmployeeCourseLesson;
}
