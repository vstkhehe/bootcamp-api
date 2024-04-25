package br.com.frwk.course.services;

import br.com.frwk.course.domain.Answer;

import java.util.Optional;

public interface AnswerService {

    Answer save(Answer answer);

    Answer update(Answer answer);

    Optional<Answer> findById(Long id);

    void delete(Answer answer);
}
