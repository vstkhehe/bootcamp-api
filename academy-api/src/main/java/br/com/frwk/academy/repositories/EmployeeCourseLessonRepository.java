package br.com.frwk.academy.repositories;

import br.com.frwk.academy.domain.EmployeeCourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCourseLessonRepository extends JpaRepository<EmployeeCourseLesson, Long> {
}
