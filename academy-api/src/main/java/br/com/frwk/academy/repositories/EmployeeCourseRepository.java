package br.com.frwk.academy.repositories;

import br.com.frwk.academy.domain.EmployeeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCourseRepository extends JpaRepository<EmployeeCourse, Long> {
}
