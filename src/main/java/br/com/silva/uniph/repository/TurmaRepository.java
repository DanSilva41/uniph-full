package br.com.silva.uniph.repository;

import br.com.silva.uniph.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data JPA repository for the Turma entity.
 *
 * @author Danilo Silva P.
 */
public interface TurmaRepository extends JpaRepository<Turma, Long>, JpaSpecificationExecutor<Turma> {

}
