package br.com.frwk.academy.resources;

import br.com.frwk.academy.domain.EmployeeCourse;
import br.com.frwk.academy.dtos.SaveEmployeeDto;
import br.com.frwk.academy.services.EmployeeCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee-course")
public class EmployeeCourseResource {

    @Autowired
    private EmployeeCourseService employeeCourseService;

    @PostMapping
    public ResponseEntity saveEmployeeCourse(@RequestBody SaveEmployeeDto obj) throws URISyntaxException {
        try {
            EmployeeCourse entity = new EmployeeCourse();
            BeanUtils.copyProperties(obj, entity);
            entity.setDtStarted(LocalDate.now());
            entity = employeeCourseService.save(entity);
            return ResponseEntity.created(new URI("/api/employee-course/" + entity.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeCourse(@PathVariable("id") Long id) {
        return employeeCourseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeCourse(@PathVariable Long id) {
        return employeeCourseService.findById(id).map(entity -> {
            employeeCourseService.delete(entity);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployeeCourse(@RequestBody SaveEmployeeDto obj,
                                               @PathVariable Long id) {
        return employeeCourseService.findById(id).map(entity -> {
            try {
                BeanUtils.copyProperties(obj, entity);
                entity = employeeCourseService.save(entity);
                return ResponseEntity.ok(entity);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }
}