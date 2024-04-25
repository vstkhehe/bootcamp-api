package br.com.frwk.academy.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee_course_lesson_answer_tb")
@Data
public class EmployeeCourseLessonAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long questionId;
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "employee_course_lesson_id")
    private EmployeeCourseLesson employeeCourseLesson;

}
