package br.com.frwk.academy.services.impl;

import br.com.frwk.academy.domain.EmployeeCourseSkill;
import br.com.frwk.academy.repositories.EmployeeCourseSkillRepository;
import br.com.frwk.academy.services.EmployeeCourseSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeCourseSkillServiceImpl implements EmployeeCourseSkillService {

    @Autowired
    private EmployeeCourseSkillRepository employeeCourseSkillRepository;

    @Override
    public EmployeeCourseSkill save(EmployeeCourseSkill entity) {
        return employeeCourseSkillRepository.save(entity);
    }

    @Override
    public Optional<EmployeeCourseSkill> findById(Long id) {
        return employeeCourseSkillRepository.findById(id);
    }

    @Override
    public void delete(EmployeeCourseSkill entity) {
        employeeCourseSkillRepository.delete(entity);
    }
}
