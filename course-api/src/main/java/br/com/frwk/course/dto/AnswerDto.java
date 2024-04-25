package br.com.frwk.course.dto;

import lombok.Data;

@Data
public class AnswerDto {

    private Long id;

    private String content;

    private Boolean active;

    private Boolean isCorrect;

    private QuestionDto question;
}