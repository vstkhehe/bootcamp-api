package br.com.frwk.badge.services.impl;

import br.com.frwk.badge.domain.Badge;
import br.com.frwk.badge.dto.BadgeDto;
import br.com.frwk.badge.repositories.BadgeRepository;
import br.com.frwk.badge.services.BadgeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BadgeImpl implements BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Override
    public Badge save(BadgeDto badgeDto) {
        Badge badge = new Badge();
        BeanUtils.copyProperties(badgeDto, badge);
        badge.setActive(false);
        return badgeRepository.save(badge);
    }

    @Override
    public Optional<Badge> findById(Long id) {
        return badgeRepository.findById(id);
    }

    @Override
    public Badge update(Badge badge) {
        Objects.requireNonNull(badge.getId());
        return badgeRepository.save(badge);
    }

    @Override
    public void delete(Long id) {
        badgeRepository.deleteById(id);
    }

    @Override
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

}
