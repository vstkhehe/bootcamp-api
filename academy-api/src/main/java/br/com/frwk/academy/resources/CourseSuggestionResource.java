package br.com.frwk.academy.resources;

import br.com.frwk.academy.domain.CourseSuggestion;
import br.com.frwk.academy.dtos.SaveCourseSuggestionDto;
import br.com.frwk.academy.services.CourseSuggestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/course-suggestion")
public class CourseSuggestionResource {

    @Autowired
    private CourseSuggestionService courseSuggestionService;


    @PostMapping
    public ResponseEntity saveCourseSuggestion (@RequestBody SaveCourseSuggestionDto dto){
        try {
            CourseSuggestion courseEntity = new CourseSuggestion();
            BeanUtils.copyProperties(dto, courseEntity);
            courseEntity = courseSuggestionService.save(courseEntity);

            return ResponseEntity.created(new URI("/api/course-suggestion/" + courseEntity.getId())).build();

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseSuggestion(@PathVariable("id") Long id){
        return courseSuggestionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourseSuggestion(@PathVariable("id") Long id){
        Optional<CourseSuggestion> courseSuggestion = courseSuggestionService.findById(id);

        if(courseSuggestion.isPresent()){
            courseSuggestionService.delete(id);
           return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
