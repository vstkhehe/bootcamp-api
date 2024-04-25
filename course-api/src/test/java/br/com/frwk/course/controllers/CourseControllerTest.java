package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Course;
import br.com.frwk.course.dto.SaveCourseDto;
import br.com.frwk.course.repositories.CourseRepository;
import br.com.frwk.course.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class CourseControllerTest {

    private static final String COURSE_URL = "/api/course";
    private static final String COURSE_URL_ID = "/api/course".concat("/{id}");

    private static final Long ID_VALIDO = 1l;

    private static final Long ID_INVALIDO = 999l;

    @Autowired
    private MockMvc mvn;

    @Autowired
    private CourseRepository repository;

    @Test
    void createCourse() throws Exception {
        SaveCourseDto course = getSaveCourseDto();
        mvn.perform(post(COURSE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(course)))
                .andExpect(status().isCreated());
    }

    @Test
    void getCourseById() throws Exception {
        repository.save(getCourse());
        mvn.perform(get(COURSE_URL_ID, ID_VALIDO)).andExpect(status().isOk());
    }

    @Test
    void getCourseByIdInvalid() throws Exception {
        repository.save(getCourse());
        mvn.perform(get(COURSE_URL_ID, ID_INVALIDO)).andExpect(status().isNotFound());
    }

    @Test
    void deleteCourse() throws Exception {
        repository.save(getCourse());
        mvn.perform(delete(COURSE_URL_ID, ID_VALIDO)).andExpect(status().isNoContent());
    }

    @Test
    void deleteNotFoundCourse() throws Exception {
        mvn.perform(delete(COURSE_URL_ID, ID_INVALIDO)).andExpect(status().isNotFound());
    }

    @Test
    void updateCourse() throws Exception {
        Course saveCourse = repository.save(getCourse());
        SaveCourseDto saveCourseDto = new SaveCourseDto();
        saveCourseDto.setName("update name");
        saveCourseDto.setDescription("update description");
        mvn.perform(put(COURSE_URL_ID, saveCourse.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(MapperUtil.convertObjectToJsonBytes(saveCourseDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateNotFoundCourse() throws Exception {
        SaveCourseDto saveCourseDto = new SaveCourseDto();
        saveCourseDto.setName("update name");
        saveCourseDto.setDescription("update description");
        mvn.perform(put(COURSE_URL_ID, ID_INVALIDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(saveCourseDto)))
                .andExpect(status().isNotFound());
    }

    private SaveCourseDto getSaveCourseDto() {
        SaveCourseDto dto = new SaveCourseDto();
        dto.setName("primeiro-curso");
        dto.setDescription("description");
        return dto;
    }

    private Course getCourse() {
        return Course.builder().name("primeiro-curso").description("description").build();
    }

}