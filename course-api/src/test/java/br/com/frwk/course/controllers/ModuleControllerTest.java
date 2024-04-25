package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Course;
import br.com.frwk.course.dto.SaveModuleDto;
import br.com.frwk.course.repositories.CourseRepository;
import br.com.frwk.course.repositories.ModuleRepository;
import br.com.frwk.course.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.frwk.course.domain.Module;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class ModuleControllerTest {

    private static final String MODULE_URL = "/api/module";
    private static final String MODULE_URL_ID = "/api/module".concat("/{id}");
    private static final String COURSE_URL_ID = "/api/course".concat("/{id}");

    private static final Long ID_VALIDO = 1l;

    private static final Long ID_INVALIDO = 999l;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    void createModule() throws Exception {
        Course course = courseRepository.save(createCourse());

        SaveModuleDto moduleDto = getSaveModuleDto();

        moduleDto.setIdCourse(course.getId());
        mvc.perform(post(MODULE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(moduleDto)))
                .andExpect(status().isCreated());

    }

    @Test
    public void findModuleById() throws Exception {
        moduleRepository.save(getModule());
        mvc.perform(get(MODULE_URL_ID, ID_VALIDO))
                .andExpect(status().isOk());
    }

    @Test
    public void findModuleByInvalidId() throws Exception {
        moduleRepository.save(getModule());
        mvc.perform(get(MODULE_URL_ID, ID_INVALIDO))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteModule() throws Exception {
        moduleRepository.save(getModule());
        mvc.perform(delete(MODULE_URL_ID, ID_VALIDO))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateModule() throws Exception {
        moduleRepository.save(getModule());
        mvc.perform(MockMvcRequestBuilders.put(MODULE_URL_ID, ID_VALIDO)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(getModule()))
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void getModuleByCourse() throws Exception {
        Course course = courseRepository.save(createCourse());
        moduleRepository.save(getModule());

        mvc.perform(get(COURSE_URL_ID, course.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNotFoundModule() throws Exception {
        mvc.perform(delete(MODULE_URL_ID, ID_INVALIDO)).andExpect(status().isNotFound());

    }

    @Test
    void updateNotFoundModule() throws Exception {
        SaveModuleDto saveModuleDto = new SaveModuleDto();
        saveModuleDto.setName("update name");
        saveModuleDto.setDescription("update description");
        mvc.perform(put(MODULE_URL_ID, ID_INVALIDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(saveModuleDto)))
                .andExpect(status().isNotFound());
    }

    private SaveModuleDto getSaveModuleDto() {
        SaveModuleDto dto = new SaveModuleDto();
        dto.setName("module");
        dto.setDescription("description");
        dto.setActive(true);
        return dto;
    }

    private Module getModule() {
        Module module = Module.builder()
                .name("teste")
                .description("teste")
                .active(true)
                .course(null)
                .lsLesson(null)
                .build();
        return module;
    }

    private Course createCourse() {
        return Course.builder().name("primeiro-curso").description("description").build();
    }
}