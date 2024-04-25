package br.com.frwk.badge.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_badge_rule")
@Data
public class BadgeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean active;
    private Integer amoundNeeded;
    @OneToOne(mappedBy = "badgeRule")
    private Badge badge;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "badge_rule_type_id", referencedColumnName = "id")
    private BadgeRuleType badgeRuleType;
}
