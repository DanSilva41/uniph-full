package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Discipline;
import br.com.silva.uniph.service.DisciplineService;
import br.com.silva.uniph.web.rest.util.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
public class DisciplineResource {

    @Autowired
    private DisciplineService disciplineService;

    /**
     * GET  /api/disciplinas : buscar todos as disciplinas.
     *
     * @return status 200 (OK) e a lista de todas as disciplinas
     */
    @GetMapping
    public Collection<Discipline> listar() {
        log.info("REST request para buscar todas as disciplinas");
        return this.disciplineService.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Discipline> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Disciplina: {}", codigo);
        Optional<Discipline> disciplinaRetornada = this.disciplineService.findByCode(codigo);
        return disciplinaRetornada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/disciplinas : Cria uma nova disciplina.
     *
     * @param discipline : a disciplina a ser criada
     * @return a ResponseEntity com status 201 (Criado) e com o corpo da nova disciplina
     */
    @PostMapping
    public ResponseEntity<Discipline> cadastrar(@RequestBody @Valid Discipline discipline) {
        log.info("Requisição REST para salvar Disciplina: {}", discipline);
        Discipline disciplineSaved = this.disciplineService.saveDiscipline(discipline);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(disciplineSaved.getCode()).toUri();
        return ResponseEntity.created(uri).body(disciplineSaved);
    }

    /**
     * POST  /api/disciplinas/id : Excluir uma disciplina.
     *
     * @param code : id da disciplina ser excluida
     * @return a ResponseEntity com status 201 (Criado)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> excluir(@PathVariable Long code) {
        log.info("Requisição REST para excluir Disciplina: {}", code);
        this.disciplineService.deleteDiscipline(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("disciplina.deletado", String.valueOf(code))).build();
    }

}
