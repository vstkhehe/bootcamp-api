package br.com.frwk.badge.resources;

import br.com.frwk.badge.domain.Badge;
import br.com.frwk.badge.dto.BadgeDto;
import br.com.frwk.badge.services.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/badge")
public class BadgeResource {

    @Autowired
    private BadgeService badgeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Badge> getAllAContacts(){
        return badgeService.getAllBadges();
    }


    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id){
        return badgeService.findById(id)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody BadgeDto badgeDto) throws URISyntaxException {
        Badge badgeSaved = badgeService.save(badgeDto);
        return ResponseEntity.created(new URI("/api/badge" + badgeSaved.getId())).body(badgeSaved);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Badge badge) {
        Badge save = badgeService.update(badge);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Badge> badge = badgeService.findById(id);

        if (!badge.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        badgeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
