package br.com.frwk.academy.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_suggestions_status")
@Data
public class SuggestionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
}