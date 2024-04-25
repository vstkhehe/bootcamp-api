package br.com.frwk.course.services.impl;

import br.com.frwk.course.domain.Answer;
import br.com.frwk.course.repositories.AnswerRepository;
import br.com.frwk.course.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer save(Answer answer){
        return answerRepository.save(answer);
    }

    @Override
    public Answer update(Answer answer){
        Answer answerToUpdate = answerRepository.getReferenceById(answer.getId());
        return answerRepository.saveAndFlush(answerToUpdate);
    }

    public Optional<Answer> findById(Long id){
        return answerRepository.findById(id);
    }

    @Override
    public void delete(Answer answer){
        answerRepository.delete(answer);
    }
}
