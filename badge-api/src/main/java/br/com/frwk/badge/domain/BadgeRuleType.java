package br.com.frwk.badge.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_badge_rule_type")
@Data
public class BadgeRuleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean active;
    @OneToOne(mappedBy = "badgeRuleType")
    private BadgeRule badgeRule;
}
