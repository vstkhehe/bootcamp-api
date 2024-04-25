package br.com.frwk.badge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.frwk.badge.domain.Badge;
import br.com.frwk.badge.repositories.BadgeRepository;
import br.com.frwk.badge.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class BadgeResourceTest {

    private static final String BADGE_RESOURCE_URL = "/api/badge";
    private static final String BADGE_RESOURCE_URL_ID = BADGE_RESOURCE_URL + "/{id}";
    private final Long ID = 1L;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private MockMvc badgeController;

    @Test
    void creatingBadge_Expect_Correct_Data() throws Exception {
        Badge badge = createBadgeWithEmployeeBadge();

        badgeController.perform(post(BADGE_RESOURCE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(badge))).andExpect(status().isCreated())
                .andReturn().getResponse();
        Optional<Badge> badgeSaved = badgeRepository.findById(ID);

        assertThat(badgeSaved.get().getName()).isEqualTo("zezinho das montanhas");
        assertThat(badgeSaved.get().getDescription()).isEqualTo("aqui tem uma descrição");
    }

    @Test
    void searchBadge_Expect_Correct_Data() throws Exception {
        Badge badge = createBadgeWithEmployeeBadge();
        badgeRepository.save(badge);
        badgeController.perform(get(BADGE_RESOURCE_URL)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].name").value(hasItem("zezinho das montanhas")))
                .andExpect(jsonPath("$.[*].description").value(hasItem("aqui tem uma descrição")));

    }

    @Test
    void saveAndUpdateBadge_Expect_Correct_Data() throws Exception {
        Badge badge = createBadgeWithEmployeeBadge();

        Badge badgeSaved = badgeRepository.save(badge);

        assertThat(badgeSaved.getId()).isEqualTo(ID);

        badgeSaved.setDescription("outra descrição");
        badgeSaved.setName("zezinho dos mares");

        badgeController.perform(put(BADGE_RESOURCE_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(badgeSaved)))
                .andExpect(status().isOk());

        assertThat(badgeSaved.getDescription()).isEqualTo("outra descrição");
        assertThat(badgeSaved.getName()).isEqualTo("zezinho dos mares");

        badgeSaved.setDescription("aqui tem uma descrição");
        badgeSaved.setName("zezinho das montanhas");

        badgeRepository.save(badgeSaved);

    }

    @Test
    void saveBadgeEndThenDelete_Expect_No_content() throws Exception {
        Badge badge = createBadgeWithEmployeeBadge();
        Badge badgeSaved = badgeRepository.save(badge);

        badgeController
                .perform(delete(BADGE_RESOURCE_URL_ID, badgeSaved.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(badgeSaved)))
                .andExpect(status().isNoContent());
    }

    public Badge createBadgeWithEmployeeBadge() {

        Badge badge = new Badge();
        badge.setName("zezinho das montanhas");
        badge.setDescription("aqui tem uma descrição");
        badge.setActive(true);

        return badge;

    }

}