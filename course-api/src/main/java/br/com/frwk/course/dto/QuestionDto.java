package br.com.frwk.course.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {

    private Long id;

    private String content;

    private Boolean active;

    private LessonDto lesson;

    private List<AnswerDto> lsAnswer;
}