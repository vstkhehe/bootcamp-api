package br.com.frwk.course.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaveQuestionDto {
    private String content;
    private Boolean active;
    private Long idLesson;
}
