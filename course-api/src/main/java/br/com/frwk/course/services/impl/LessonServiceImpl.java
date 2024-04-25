package br.com.frwk.course.services.impl;

import br.com.frwk.course.domain.Lesson;
import br.com.frwk.course.repositories.LessonRepository;
import br.com.frwk.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Lesson save(Lesson lesson){
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson update(Lesson lesson){
        Lesson lessonToUpdate = lessonRepository.getReferenceById(lesson.getId());
        return lessonRepository.saveAndFlush(lessonToUpdate);
    }
    @Override
    public Optional<Lesson> findById(Long id){
        return lessonRepository.findById(id);
    }
    @Override
    public void delete(Long id){
        Objects.requireNonNull(id);
        lessonRepository.deleteById(id);
    }

}
