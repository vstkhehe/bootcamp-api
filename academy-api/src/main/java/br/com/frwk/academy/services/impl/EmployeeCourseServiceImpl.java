package br.com.frwk.academy.services.impl;

import br.com.frwk.academy.domain.EmployeeCourse;
import br.com.frwk.academy.repositories.EmployeeCourseRepository;
import br.com.frwk.academy.services.EmployeeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeCourseServiceImpl implements EmployeeCourseService {

    @Autowired
    private EmployeeCourseRepository employeeCourseRepository;

    @Override
    public EmployeeCourse save(EmployeeCourse entity) {
        return employeeCourseRepository.save(entity);
    }

    @Override
    public Optional<EmployeeCourse> findById(Long id) {
        return employeeCourseRepository.findById(id);
    }

    @Override
    public void delete(EmployeeCourse entity) {
        employeeCourseRepository.delete(entity);
    }
}
