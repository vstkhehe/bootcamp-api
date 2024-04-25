package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Course;
import br.com.frwk.course.domain.Lesson;
import br.com.frwk.course.domain.Module;
import br.com.frwk.course.dto.SaveLessonDto;
import br.com.frwk.course.repositories.CourseRepository;
import br.com.frwk.course.repositories.LessonRepository;
import br.com.frwk.course.repositories.ModuleRepository;
import br.com.frwk.course.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
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
class LessonControllerTest {

    private static final String LESSON_URL = "/api/lesson";

    private static final String LESSON_URL_ID = "/api/lesson".concat("/{id}");

    private static final Long ID_INVALIDO = 999l;
    private static final Long ID_VALIDO = 1l;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    void saveLesson() throws Exception {
        Module module = moduleRepository.save(createModule());

        SaveLessonDto lessonDto = getSaveLessonDto();

        lessonDto.setIdModule(module.getId());

        mvc.perform(post(LESSON_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(lessonDto)))
                .andExpect(status().isCreated());

    }

    @Test
    void findLessonById() throws Exception {
        Lesson lesson = lessonRepository.save(getLesson());
        mvc.perform(get(LESSON_URL_ID, lesson.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void findLessonByInvalidId() throws Exception {
        lessonRepository.save(getLesson());
        mvc.perform(get(LESSON_URL_ID, ID_INVALIDO))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteLesson() throws Exception {
        lessonRepository.save(getLesson());
        mvc.perform(delete(LESSON_URL_ID, ID_VALIDO))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateLesson() throws Exception {
        lessonRepository.save(getLesson());
        mvc.perform(MockMvcRequestBuilders.put(LESSON_URL_ID, ID_VALIDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(getLesson()))
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deleteNotFoundLesson() throws Exception {
        mvc.perform(delete(LESSON_URL_ID, ID_INVALIDO)).andExpect(status().isNotFound());
    }

    @Test
    void updateNotFoundLesson() throws Exception {
        SaveLessonDto saveLessonDto = new SaveLessonDto();
        saveLessonDto.setName("update name");
        saveLessonDto.setContent("update content");
        mvc.perform(put(LESSON_URL_ID, ID_INVALIDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(saveLessonDto)))
                .andExpect(status().isNotFound());
    }

    private SaveLessonDto getSaveLessonDto() {
        SaveLessonDto dto = new SaveLessonDto();
        dto.setName("module");
        dto.setContent("content");
        return dto;
    }

    private Lesson getLesson(){
        Lesson lesson = Lesson.builder()
                .id(ID_VALIDO)
                .name("name")
                .content("content")
                .module(null)
                .lsQuestion(null)
                .active(true)
                .build();
        return lesson;
    }

    private Course createCourse() {
        return Course.builder()
                .name("primeiro-curso")
                .description("description")
                .build();
    }

    private Module createModule() {
        Module module = Module.builder()
                .id(ID_VALIDO)
                .name("teste")
                .description("teste")
                .active(true)
                .course(null)
                .lsLesson(null)
                .build();
        return module;
    }
}