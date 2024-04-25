package br.com.frwk.academy.services;


import br.com.frwk.academy.domain.CourseSuggestion;

import java.util.Optional;

public interface CourseSuggestionService {
    CourseSuggestion save(CourseSuggestion entity);

    Optional<CourseSuggestion> findById(Long id);


    void delete(Long id);
}
