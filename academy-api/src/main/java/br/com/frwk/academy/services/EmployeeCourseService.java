package br.com.frwk.academy.services;

import br.com.frwk.academy.domain.EmployeeCourse;

import java.util.Optional;

public interface EmployeeCourseService {
    EmployeeCourse save(EmployeeCourse entity);

    Optional<EmployeeCourse> findById(Long id);

    void delete(EmployeeCourse entity);
}
