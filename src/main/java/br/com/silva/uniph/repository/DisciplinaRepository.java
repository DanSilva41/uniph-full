package br.com.silva.uniph.repository;

import br.com.silva.uniph.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data JPA repository for the Disciplina entity.
 *
 * @author Danilo Silva P.
 */
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>, JpaSpecificationExecutor<Disciplina> {

}
