package br.com.frwk.badge.repositories;

import br.com.frwk.badge.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
