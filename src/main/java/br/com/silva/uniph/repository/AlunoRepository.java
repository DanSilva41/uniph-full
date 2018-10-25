package br.com.silva.uniph.repository;

import br.com.silva.uniph.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 *
 * @author Danilo Silva P.
 */
public interface AlunoRepository extends JpaRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno> {

    Optional<Aluno> findOneByCpf(String cpf);
}
