package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Lesson;
import br.com.frwk.course.domain.Question;
import br.com.frwk.course.dto.SaveQuestionDto;
import br.com.frwk.course.services.LessonService;
import br.com.frwk.course.services.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LessonService lessonService;

    @GetMapping("/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable("id") Long id) {
        return questionService.findById(id)
                .map(obj -> new ResponseEntity(obj, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity getQuestionByLesson(@PathVariable("id") Long id) {
        return questionService.findLessonByIdLesson(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity saveQuestion(@RequestBody SaveQuestionDto saveQuestionDto) {

        Optional<Lesson> lesson = lessonService.findById(saveQuestionDto.getIdLesson());
        if (lesson.isPresent()) {
            Question question = new Question();

            BeanUtils.copyProperties(saveQuestionDto, question);
            question.setLesson(lesson.get());
            question = questionService.save(question);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(question.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(question);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateQuestion(@Valid @RequestBody SaveQuestionDto questionDto,
                                       @PathVariable("id") Long id) {
        Optional<Question> question = questionService.findById(id);
        if (question.isPresent()) {
            Question questionSave = question.get();
            BeanUtils.copyProperties(questionDto, questionSave);
            questionService.update(questionSave);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestion(@PathVariable("id") Long id) {
        try {
            questionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
