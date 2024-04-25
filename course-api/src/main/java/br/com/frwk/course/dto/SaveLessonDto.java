package br.com.frwk.course.dto;

import lombok.Data;

@Data
public class SaveLessonDto {
    private Long idModule;
    private String name;
    private String content;
    private String videoUrl;
}
