package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Lesson;
import br.com.frwk.course.domain.Module;
import br.com.frwk.course.dto.SaveLessonDto;
import br.com.frwk.course.services.LessonService;
import br.com.frwk.course.services.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/{id}")
    public ResponseEntity findLessonById(@PathVariable("id") Long id) {
        return lessonService.findById(id).map(lesson -> ResponseEntity.ok(lesson))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity saveLesson(@RequestBody SaveLessonDto saveLessonDto) throws URISyntaxException {
        Optional<Module> module = moduleService.findById(saveLessonDto.getIdModule());
        if (module.isPresent()) {
            Lesson lesson = new Lesson();
            BeanUtils.copyProperties(saveLessonDto, lesson);
            lesson.setModule(module.get());
            Lesson saveLesson = lessonService.save(lesson);

            return ResponseEntity.created(new URI("/api/lesson/" + saveLesson.getId())).body(saveLesson);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLesson(@Valid @RequestBody SaveLessonDto lessonDto,
                                       @PathVariable("id") Long id) {
        Optional<Lesson> lesson = lessonService.findById(id);

        if (lesson.isPresent()) {
            Lesson lessonSave = lesson.get();
            BeanUtils.copyProperties(lessonDto, lessonSave);
            lessonService.update(lessonSave);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLesson(@PathVariable("id") Long id) {
        try {
            lessonService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
