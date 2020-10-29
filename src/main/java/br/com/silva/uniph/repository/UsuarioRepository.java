package br.com.silva.uniph.repository;

import br.com.silva.uniph.domain.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Usuario entity.
 *
 * @author Danilo Silva P.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    String AUTHORITIES = "authorities";

    Optional<Usuario> findOneByLogin(String login);

    @EntityGraph(attributePaths = AUTHORITIES)
    Usuario findOneWithAuthoritiesByCodigo(Long codigo);

    @EntityGraph(attributePaths = AUTHORITIES)
    Optional<Usuario> findOneWithAuthoritiesByLogin(String login);
}
