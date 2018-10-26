package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Professor;
import br.com.silva.uniph.service.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

/**
 * REST controller para gerenciar professores.
 * Esta classe acessa a entidade Professor
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/professores")
public class ProfessorResource {

    @Autowired
    private ProfessorService professorService;

    /**
     * GET  /api/professores : buscar todos os professores.
     *
     * @return status 200 (OK) e a lista de todos os professores
     */
    @GetMapping
    public Collection<Professor> listar() {
        log.info("REST request para buscar todos os professores");
        return this.professorService.buscarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Professores: {}", codigo);
        Optional<Professor> professorRetornado = this.professorService.buscarPorCodigo(codigo);
        return professorRetornado.isPresent() ? ResponseEntity.ok(professorRetornado.get()) : ResponseEntity.notFound().build();
    }

    /**
     * POST  /api/professores : Cria um novo professor.
     *
     * @param professor : o professor a ser criado
     * @return a ResponseEntity com status 201 (Criado) e com o corpo do novo professor
     * @throws URISyntaxException se a sintaxe do URI de localização estiver incorreta
     */
    @PostMapping
    public ResponseEntity<Professor> cadastrar(@RequestBody @Valid Professor professor) throws URISyntaxException {
        log.info("Requisição REST para salvar Professor: {}", professor);
        Professor professorSalvo = this.professorService.salvar(professor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(professorSalvo.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(professorSalvo);
    }

}
