package br.com.frwk.course.services;

import br.com.frwk.course.domain.Lesson;

import java.util.Optional;

public interface LessonService {

    Lesson save(Lesson lesson);

    Lesson update(Lesson lesson);

    Optional<Lesson> findById(Long id);

    void delete(Long id);
}
