package br.com.frwk.academy.dtos;

import br.com.frwk.academy.domain.EmployeeCourse;
import lombok.Data;

@Data
public class SaveEmployeeSkillDto {
    private EmployeeCourse employeeCourse;
    private Long skill;
    private Integer exp;
    private Boolean active;
    private Integer level;
}
