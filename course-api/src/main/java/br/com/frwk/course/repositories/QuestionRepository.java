package br.com.frwk.course.repositories;

import br.com.frwk.course.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<List<Question>> findQuestionsByLesson_Id(Long id);
}
