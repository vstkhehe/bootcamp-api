package br.com.frwk.academy.resources;

import br.com.frwk.academy.domain.EmployeeCourse;
import br.com.frwk.academy.dtos.SaveEmployeeDto;
import br.com.frwk.academy.repositories.EmployeeCourseRepository;
import br.com.frwk.academy.services.EmployeeCourseService;
import br.com.frwk.academy.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeCourseResourceTest {

    private static final String EMPLOYEE_COURSE = "/api/employee-course";
    private static final String EMPLOYEE_COURSE_ID = "/api/employee-course".concat("/{id}");

    private static final Long ID_VALIDO = 1l;

    private static final Long ID_INVALIDO = 999l;

    @Autowired
    private MockMvc mvn;

    @Autowired
    private EmployeeCourseRepository employeeCourseRepository;

    @SpyBean
    private EmployeeCourseService employeeCourseService;

    @Test
    void saveEmployeeCourse() throws Exception {
        mvn.perform(MockMvcRequestBuilders.post(EMPLOYEE_COURSE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(createDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void findEployeeCourseByIdSucess() throws Exception {
        Mockito.when(employeeCourseService.findById(Mockito.anyLong())).thenReturn(Optional.of(createEntity()));
        mvn.perform(MockMvcRequestBuilders.get(EMPLOYEE_COURSE_ID, ID_VALIDO))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findEployeeCourseNotFound() throws Exception {
        mvn.perform(MockMvcRequestBuilders.get(EMPLOYEE_COURSE_ID, ID_INVALIDO))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteEployeeCourseSuccess() throws Exception {
        EmployeeCourse entity = employeeCourseRepository.save(createEntity());
        mvn.perform(MockMvcRequestBuilders.delete(EMPLOYEE_COURSE_ID, entity.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deleteEployeeCourseNotFound() throws Exception {
        mvn.perform(MockMvcRequestBuilders.delete(EMPLOYEE_COURSE_ID, ID_INVALIDO))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateEployeeCourseSuccess() throws Exception {
        EmployeeCourse entity = employeeCourseRepository.save(createEntity());
        mvn.perform(MockMvcRequestBuilders.put(EMPLOYEE_COURSE_ID, entity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                    .content(MapperUtil.convertObjectToJsonBytes(createDto()))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateEployeeCourseNotFound() throws Exception {
        mvn.perform(MockMvcRequestBuilders.put(EMPLOYEE_COURSE_ID, ID_INVALIDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperUtil.convertObjectToJsonBytes(createDto()))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    private SaveEmployeeDto createDto() {
        SaveEmployeeDto dto = new SaveEmployeeDto();
        dto.setIdCourse(1l);
        dto.setIdEmployee(1l);
        dto.setActive(false);
        return dto;
    }

    private EmployeeCourse createEntity() {
        EmployeeCourse entity = new EmployeeCourse();
        entity.setId(ID_VALIDO);
        entity.setIdCourse(1l);
        entity.setIdEmployee(1l);
        entity.setActive(true);
        return entity;
    }
}