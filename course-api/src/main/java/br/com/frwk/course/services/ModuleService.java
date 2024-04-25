package br.com.frwk.course.services;

import br.com.frwk.course.domain.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleService {
    Module save(Module module);
    Module update(Module module);
    Optional<Module> findById(Long id);
    void delete(Long id);
    Optional<List<Module>> getModuleByCourse(Long idCourse);
}
