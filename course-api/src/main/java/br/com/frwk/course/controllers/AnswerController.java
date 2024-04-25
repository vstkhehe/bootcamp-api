package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Answer;
import br.com.frwk.course.domain.Question;
import br.com.frwk.course.dto.SaveAnswerDto;
import br.com.frwk.course.services.AnswerService;
import br.com.frwk.course.services.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity findAnswerById(@PathVariable("id") Long id) {
        return answerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity saveAnswer(@RequestBody SaveAnswerDto saveAnswerDto) throws URISyntaxException {
        Optional<Question> question = questionService.findById(saveAnswerDto.getIdQuestion());
        if (!question.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Answer answer = new Answer();
        BeanUtils.copyProperties(saveAnswerDto, answer);
        answer.setQuestion(question.get());
        answer = answerService.save(answer);
        return ResponseEntity.created(new URI("/api/answer/"+answer.getId())).body(answer);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAnswer(@RequestBody SaveAnswerDto saveAnswerDto,
                                       @PathVariable("id") Long id) {
        Optional<Answer> opAnswer = answerService.findById(id);
        if (!opAnswer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Answer answer = opAnswer.get();
        BeanUtils.copyProperties(saveAnswerDto, answer);
        answerService.update(answer);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("id")
    public ResponseEntity deleteAnswer(@PathVariable("id") Long id) {
        Optional<Answer> answer = answerService.findById(id);
        if (!answer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        answerService.delete(answer.get());
        return ResponseEntity.noContent().build();
    }

}
