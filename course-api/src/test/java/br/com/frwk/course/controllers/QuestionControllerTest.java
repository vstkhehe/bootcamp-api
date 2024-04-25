package br.com.frwk.course.controllers;

import br.com.frwk.course.domain.Course;
import br.com.frwk.course.domain.Lesson;
import br.com.frwk.course.domain.Module;
import br.com.frwk.course.domain.Question;
import br.com.frwk.course.dto.SaveQuestionDto;
import br.com.frwk.course.repositories.CourseRepository;
import br.com.frwk.course.repositories.LessonRepository;
import br.com.frwk.course.repositories.ModuleRepository;
import br.com.frwk.course.repositories.QuestionRepository;
import br.com.frwk.course.services.QuestionService;
import br.com.frwk.course.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class QuestionControllerTest {

    private static final String QUESTION_URL = "/api/question";
    private static final String QUESTION_URL_ID = "/api/question".concat("/{id}");
    private static final Long ID_INVALIDO = 99l;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private MockMvc mvc;

    @Spy
    private QuestionService questionService;

    @Test
    void createQuestion() throws Exception {
        SaveQuestionDto saveQuestionDto = new SaveQuestionDto();
        saveQuestionDto.setIdLesson(saveCourseAndModuleAndLesson().getId());
        saveQuestionDto.setContent("content");
        saveQuestionDto.setActive(true);
        mvc.perform(
                MockMvcRequestBuilders.post(QUESTION_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(saveQuestionDto))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void findQuestionById() throws Exception {
        Question question = persistQuestion();
        mvc.perform(MockMvcRequestBuilders.get(QUESTION_URL_ID, question.getId())).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateQuestionNotFound() throws Exception {
        SaveQuestionDto saveQuestionDto = new SaveQuestionDto();
        mvc.perform(
                MockMvcRequestBuilders.put(QUESTION_URL_ID, ID_INVALIDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(saveQuestionDto))
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteQuestion() throws Exception {
        Question question = persistQuestion();
        mvc.perform(
                MockMvcRequestBuilders.delete(QUESTION_URL_ID, question.getId())
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deleteQuestionException() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.delete(QUESTION_URL_ID, ID_INVALIDO)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private Lesson saveCourseAndModuleAndLesson() {
        Course course = getCourse();
        Module module = new Module();
        Lesson lesson = new Lesson();
        course = courseRepository.save(course);
        module.setName("module");
        module.setCourse(course);
        module = moduleRepository.save(module);
        lesson.setName("lesson test");
        lesson.setContent("cotent");
        lesson.setModule(module);
        lesson = lessonRepository.save(lesson);
        return lesson;
    }

    private Question persistQuestion() {
        Question question = new Question();
        question.setLesson(saveCourseAndModuleAndLesson());
        question.setContent("content");
        question.setActive(true);
        return questionRepository.save(question);
    }

    private Course getCourse() {
        Course course = new Course();
        course.setName("course");
        course.setDescription("description");
        course.setActive(true);
        return course;
    }
}