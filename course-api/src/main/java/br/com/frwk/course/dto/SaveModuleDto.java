package br.com.frwk.course.dto;

import lombok.Data;

@Data
public class SaveModuleDto {
    private String name;
    private String description;
    private Boolean active;
    private Long idCourse;
}
