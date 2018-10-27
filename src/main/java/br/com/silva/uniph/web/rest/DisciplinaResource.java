package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Disciplina;
import br.com.silva.uniph.service.DisciplinaService;
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
 * REST controller para gerenciar disciplinas.
 * Esta classe acessa a entidade Disciplina
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaResource {

    @Autowired
    private DisciplinaService disciplinaService;

    /**
     * GET  /api/disciplinas : buscar todos as disciplinas.
     *
     * @return status 200 (OK) e a lista de todas as disciplinas
     */
    @GetMapping
    public Collection<Disciplina> listar() {
        log.info("REST request para buscar todas as disciplinas");
        return this.disciplinaService.buscarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Disciplina> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Disciplina: {}", codigo);
        Optional<Disciplina> disciplinaRetornada = this.disciplinaService.buscarPorCodigo(codigo);
        return disciplinaRetornada.isPresent() ? ResponseEntity.ok(disciplinaRetornada.get()) : ResponseEntity.notFound().build();
    }

    /**
     * POST  /api/disciplinas : Cria uma nova disciplina.
     *
     * @param disciplina : a disciplina a ser criada
     * @return a ResponseEntity com status 201 (Criado) e com o corpo da nova disciplina
     * @throws URISyntaxException se a sintaxe do URI de localização estiver incorreta
     */
    @PostMapping
    public ResponseEntity<Disciplina> cadastrar(@RequestBody @Valid Disciplina disciplina) throws URISyntaxException {
        log.info("Requisição REST para salvar Disciplina: {}", disciplina);
        Disciplina disciplinaSalva = this.disciplinaService.salvar(disciplina);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(disciplinaSalva.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(disciplinaSalva);
    }


}
