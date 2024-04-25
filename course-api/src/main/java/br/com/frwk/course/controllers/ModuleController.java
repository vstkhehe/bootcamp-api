package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Course;
import br.com.frwk.course.domain.Module;
import br.com.frwk.course.dto.SaveModuleDto;
import br.com.frwk.course.services.CourseService;
import br.com.frwk.course.services.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/module")
public class ModuleController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/{id}")
    public ResponseEntity<Module> findModuleById(@PathVariable("id") Long id) {
        return moduleService.findById(id)
                .map(module -> new ResponseEntity(module, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity saveModule(@RequestBody SaveModuleDto saveModuleDto) {

        Optional<Course> course = courseService.findById(saveModuleDto.getIdCourse());
        if (course.isPresent()) {
            Module module = new Module();
            BeanUtils.copyProperties(saveModuleDto, module);
            module.setCourse(course.get());
            module = moduleService.save(module);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(module.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(module);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateModule(@Valid @RequestBody SaveModuleDto moduleDto,
                                       @PathVariable("id") Long id) {
        Optional<Module> module = moduleService.findById(id);
        if (module.isPresent()) {
            Module moduleSave = module.get();
            BeanUtils.copyProperties(moduleDto, moduleSave);
            moduleService.update(moduleSave);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/course/{id}")
    public ResponseEntity getModuleByCourse(@PathVariable("id") Long id) {
        Optional<List<Module>> moduleByCourse = moduleService.getModuleByCourse(id);
        return moduleByCourse
                .map(module -> new ResponseEntity(module, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteModule(@PathVariable("id") Long id) {
        try {
            moduleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
