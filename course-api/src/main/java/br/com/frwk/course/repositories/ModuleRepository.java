package br.com.frwk.course.repositories;

import br.com.frwk.course.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
        Optional<List<Module>> findModuleByCourse_Id(Long id);
}
