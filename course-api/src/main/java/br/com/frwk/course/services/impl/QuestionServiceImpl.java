package br.com.frwk.course.services.impl;

import br.com.frwk.course.domain.Question;
import br.com.frwk.course.repositories.QuestionRepository;
import br.com.frwk.course.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question update(Question question) {
        Objects.requireNonNull(question.getId());
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(Long id) {
        Optional<Question> obj = questionRepository.findById(id);
        if (obj.isPresent()) {
            return obj;
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        questionRepository.deleteById(id);
    }

    @Override
    public Optional<List<Question>> findLessonByIdLesson(Long id) {
        return questionRepository.findQuestionsByLesson_Id(id);
    }
}
