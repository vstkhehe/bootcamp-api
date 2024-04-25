package br.com.frwk.academy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="tb_course_suggestion")
@Data
public class CourseSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long employee;

    @NotNull
    @JsonIgnore
    private Long course;

    boolean active;

    @JsonIgnore
    private SuggestionStatus status;
}
