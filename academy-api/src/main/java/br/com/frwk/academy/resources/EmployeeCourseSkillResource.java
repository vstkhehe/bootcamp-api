package br.com.frwk.academy.resources;

import br.com.frwk.academy.domain.EmployeeCourseSkill;
import br.com.frwk.academy.dtos.SaveEmployeeDto;
import br.com.frwk.academy.services.EmployeeCourseSkillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/employee-course-skill")
public class EmployeeCourseSkillResource {

    @Autowired
    private EmployeeCourseSkillService employeeCourseSkillService;

    @PostMapping
    public ResponseEntity saveEmployeeCourseSkill(@RequestBody SaveEmployeeDto obj) throws URISyntaxException {
        try {
            EmployeeCourseSkill entity = new EmployeeCourseSkill();
            BeanUtils.copyProperties(obj, entity);
            entity = employeeCourseSkillService.save(entity);
            return ResponseEntity.created(new URI("/api/employee-course-skill/" + entity.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getEmployeeCourseSkill(@PathVariable("id") Long id) {
        return employeeCourseSkillService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeCourseSkill(@PathVariable Long id) {
        return employeeCourseSkillService.findById(id).map(entity -> {
            employeeCourseSkillService.delete(entity);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}