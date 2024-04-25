package br.com.frwk.academy.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee_course_lesson_tb")
@Data
public class EmployeeCourseLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    private LocalDate dtStarted;
    private LocalDate dtFinished;

    @ManyToOne
    @JoinColumn(name = "employee_course_id")
    EmployeeCourse employeeCourse;

    @OneToMany(mappedBy = "employeeCourseLesson")
    List<EmployeeCourseLessonAnswer> lsEmployeeCourseLessonAnswer;

}
