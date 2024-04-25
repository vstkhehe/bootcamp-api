package br.com.frwk.badge.services;

import br.com.frwk.badge.domain.Badge;
import br.com.frwk.badge.dto.BadgeDto;

import java.util.List;
import java.util.Optional;

public interface BadgeService {
    Badge save(BadgeDto badge);
    Optional<Badge> findById(Long id);
    Badge update(Badge badge);
    void delete(Long id);

    List<Badge> getAllBadges();
}
