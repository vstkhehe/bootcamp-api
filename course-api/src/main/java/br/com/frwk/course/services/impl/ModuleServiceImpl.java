package br.com.frwk.course.services.impl;

import br.com.frwk.course.domain.Module;
import br.com.frwk.course.repositories.ModuleRepository;
import br.com.frwk.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public Module update(Module module) {
        Objects.requireNonNull(module.getId());
        return moduleRepository.save(module);
    }

    @Override
    public Optional<Module> findById(Long id) {
        return moduleRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        moduleRepository.deleteById(id);
    }

    @Override
    public Optional<List<Module>> getModuleByCourse(Long idCourse) {
        Optional<List<Module>> lsModules = moduleRepository.findModuleByCourse_Id(idCourse);
        return lsModules;
    }
}
