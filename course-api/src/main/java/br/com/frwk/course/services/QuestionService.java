package br.com.frwk.course.services;

import br.com.frwk.course.domain.Lesson;
import br.com.frwk.course.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question save(Question question);

    Question update(Question question);

    Optional<Question> findById(Long id);

    void delete(Long id);

    Optional<List<Question>> findLessonByIdLesson(Long id);
}
