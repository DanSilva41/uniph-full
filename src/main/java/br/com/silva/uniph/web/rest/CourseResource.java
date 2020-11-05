package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Course;
import br.com.silva.uniph.service.CourseService;
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
 * REST controller para gerenciar turmas.
 * Esta classe acessa a entidade Turma
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/turmas")
public class CourseResource {

    @Autowired
    private CourseService courseService;

    /**
     * GET  /api/turmas : buscar todos as turmas.
     *
     * @return status 200 (OK) e a lista de todas as turmas
     */
    @GetMapping
    public Collection<Course> listar() {
        log.info("REST request para buscar todas as turmas");
        return this.courseService.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Course> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Turma: {}", codigo);
        Optional<Course> turmaRetornada = this.courseService.findByCode(codigo);
        return turmaRetornada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/turmas : Cria uma nova turma.
     *
     * @param turma : a turma a ser criada
     * @return a ResponseEntity com status 201 (Criado) e com o corpo da nova turma
     */
    @PostMapping
    public ResponseEntity<Course> cadastrar(@RequestBody @Valid Course turma) {
        log.info("Requisição REST para salvar Turma: {}", turma);
        Course turmaSalva = this.courseService.saveCourse(turma);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(turmaSalva.getCode()).toUri();
        return ResponseEntity.created(uri).body(turmaSalva);
    }

    /**
     * POST  /api/turmar/id : Excluir uma turma.
     *
     * @param code : id da turma ser excluida
     * @return a ResponseEntity com status 201 (Criado)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> excluir(@PathVariable Long code) {
        log.info("Requisição REST para excluir Turma: {}", code);
        this.courseService.deleteCourse(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("course.removed", String.valueOf(code))).build();
    }

}
