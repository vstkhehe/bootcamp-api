package br.com.frwk.academy.services.impl;

import br.com.frwk.academy.domain.CourseSuggestion;
import br.com.frwk.academy.repositories.CourseSuggestionRepository;
import br.com.frwk.academy.services.CourseSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseSuggestionServiceImpl implements CourseSuggestionService {

    @Autowired
    private CourseSuggestionRepository courseSuggestionRepository;

    @Override
    public CourseSuggestion save(CourseSuggestion entity) {
        return courseSuggestionRepository.save(entity);
    }

    @Override
    public Optional<CourseSuggestion> findById(Long id) {
        return courseSuggestionRepository.findById(id);
    }

    @Override
    public void delete(Long id){
        courseSuggestionRepository.deleteById(id);
    }
}
