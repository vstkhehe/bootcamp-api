package br.com.frwk.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;

    private String name;

    private String description;

    private Boolean active;

    private Date createdAt;

    private Date updatedAt;

    private List<ModuleDto> lsModule;
}
