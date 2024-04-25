package br.com.frwk.academy.dtos;

import br.com.frwk.academy.domain.EmployeeCourse;
import lombok.Data;

@Data
public class SaveCourseSuggestionDto {
    private Long employee;
    private Long course;
    private Boolean active;
}
