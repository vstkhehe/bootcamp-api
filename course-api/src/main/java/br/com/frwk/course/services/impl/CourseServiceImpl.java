package br.com.frwk.course.services.impl;

import br.com.frwk.course.domain.Course;
import br.com.frwk.course.repositories.CourseRepository;
import br.com.frwk.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course save(Course course){
        return courseRepository.save(course);
    }

    public Course update(Course course){
        Course courseToUpdate = courseRepository.getReferenceById(course.getId());
        return courseRepository.saveAndFlush(courseToUpdate);
    }

    public Optional<Course> findById(Long id){
        return courseRepository.findById(id);
    }

    public void delete(Course course){
        courseRepository.delete(course);
    }
}
