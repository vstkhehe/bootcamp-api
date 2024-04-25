package br.com.frwk.course.services;

import br.com.frwk.course.domain.Course;

import java.util.Optional;

public interface CourseService {

    Course save(Course course);

    Course update(Course course);

    Optional<Course> findById(Long id);

    void delete(Course course);
}
