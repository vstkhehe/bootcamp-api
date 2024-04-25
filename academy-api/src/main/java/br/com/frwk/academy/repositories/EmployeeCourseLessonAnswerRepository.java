package br.com.frwk.academy.repositories;

import br.com.frwk.academy.domain.EmployeeCourseLessonAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCourseLessonAnswerRepository extends JpaRepository<EmployeeCourseLessonAnswer, Long> {
}
