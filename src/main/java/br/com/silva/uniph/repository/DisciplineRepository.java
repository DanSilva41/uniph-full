package br.com.silva.uniph.repository;

import br.com.silva.uniph.domain.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Discipline entity.
 *
 * @author Danilo Silva P.
 */
@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long>, JpaSpecificationExecutor<Discipline> {

}
