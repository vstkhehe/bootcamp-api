package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Course;
import br.com.frwk.course.dto.SaveCourseDto;
import br.com.frwk.course.services.impl.CourseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        return courseService.findById(id)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody SaveCourseDto obj) throws URISyntaxException {
        Course course = new Course();
        BeanUtils.copyProperties(obj, course);
        course.setCreatedAt(LocalDate.now());
        Course courseSaved  = courseService.save(course);
        return ResponseEntity.created(new URI("api/course/" + courseSaved.getId())).body(courseSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody SaveCourseDto obj,
                                 @PathVariable("id") Long id){
        Optional<Course> course = courseService.findById(id);
        if (course.isPresent()) {
            BeanUtils.copyProperties(obj, course.get());
            course.get().setUpdatedAt(LocalDate.now());
            course.get().setId(id);
            courseService.update(course.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete (@PathVariable Long id){
        Optional<Course> course = courseService.findById(id);

        if (!course.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        courseService.delete(course.get());
        return ResponseEntity.noContent().build();

    }

}
