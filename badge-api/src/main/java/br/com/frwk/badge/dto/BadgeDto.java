package br.com.frwk.badge.dto;

import br.com.frwk.badge.domain.BadgeRule;
import br.com.frwk.badge.domain.EmployeeBadge;
import lombok.Data;

@Data
public class BadgeDto {
    private String name;
    private String description;
    private EmployeeBadge employeeBadge;
    private BadgeRule badgeRule;
}
