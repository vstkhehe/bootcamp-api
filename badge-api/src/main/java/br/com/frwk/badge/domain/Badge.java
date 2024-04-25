package br.com.frwk.badge.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_badge")
@Data
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private Boolean active;

    private String imageUrl;

    @OneToMany(mappedBy = "badge")
    private List<EmployeeBadge> lsEmployeeBadge;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "badge_rule_id", referencedColumnName = "id")
    private BadgeRule badgeRule;
}
