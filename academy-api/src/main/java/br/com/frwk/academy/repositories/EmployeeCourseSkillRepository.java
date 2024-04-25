package br.com.frwk.academy.repositories;

import br.com.frwk.academy.domain.CourseSuggestion;
import br.com.frwk.academy.domain.EmployeeCourseSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCourseSkillRepository extends JpaRepository<EmployeeCourseSkill, Long> {
}
