package br.com.frwk.academy.services;

import br.com.frwk.academy.domain.EmployeeCourse;
import br.com.frwk.academy.domain.EmployeeCourseSkill;

import java.util.Optional;

public interface EmployeeCourseSkillService {
    EmployeeCourseSkill save(EmployeeCourseSkill entity);

    Optional<EmployeeCourseSkill> findById(Long id);

    void delete(EmployeeCourseSkill entity);
}
