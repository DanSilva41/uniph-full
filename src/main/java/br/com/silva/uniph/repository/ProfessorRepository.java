package br.com.silva.uniph.repository;

import br.com.silva.uniph.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data JPA repository for the Professor entity.
 *
 * @author Danilo Silva P.
 */
public interface ProfessorRepository extends JpaRepository<Professor, Long>, JpaSpecificationExecutor<Professor> {

}
