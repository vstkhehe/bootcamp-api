package br.com.frwk.course.dto;

import br.com.frwk.course.domain.Course;
import lombok.Data;

@Data
public class ModuleDto {

    public ModuleDto(){}

    public ModuleDto(String name) {
        this.name = name;
    }

    private Long id;

    private String name;

    private String description;

    private Boolean active;

    private Course course;
}
