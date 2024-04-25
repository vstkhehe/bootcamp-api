package br.com.frwk.course.dto;

import lombok.Data;

@Data
public class SaveAnswerDto {
    private String content;
    private Boolean active;
    private Boolean isCorrect;
    private Long idQuestion;
}
