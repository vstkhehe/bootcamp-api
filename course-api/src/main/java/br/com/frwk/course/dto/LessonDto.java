package br.com.frwk.course.dto;

import br.com.frwk.course.domain.Module;
import lombok.Data;

import java.util.List;

@Data
public class LessonDto {

    private Long id;

    private String name;

    private String content;

    private Boolean active;

    private String videoUrl;

    private Module module;

    private List<QuestionDto> lsQuestion;
}