package br.com.frwk.academy.dtos;

import lombok.Data;

@Data
public class SaveEmployeeDto {
    private Long idEmployee;
    private Long idCourse;
    private Boolean active;
}
