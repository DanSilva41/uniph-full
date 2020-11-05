package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Teacher;
import br.com.silva.uniph.service.TeacherService;
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
 * REST controller para gerenciar professores.
 * Esta classe acessa a entidade Professor
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/professores")
public class TeacherResource {

    @Autowired
    private TeacherService teacherService;

    /**
     * GET  /api/professores : buscar todos os professores.
     *
     * @return status 200 (OK) e a lista de todos os professores
     */
    @GetMapping
    public Collection<Teacher> listar() {
        log.info("REST request para buscar todos os professores");
        return this.teacherService.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Teacher> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Professores: {}", codigo);
        Optional<Teacher> professorRetornado = this.teacherService.findByCode(codigo);
        return professorRetornado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/professores : Cria um novo professor.
     *
     * @param teacher : o professor a ser criado
     * @return a ResponseEntity com status 201 (Criado) e com o corpo do novo professor
     */
    @PostMapping
    public ResponseEntity<Teacher> cadastrar(@RequestBody @Valid Teacher teacher) {
        log.info("Requisição REST para salvar Professor: {}", teacher);
        Teacher teacherSaved = this.teacherService.saveTeacher(teacher);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(teacherSaved.getCode()).toUri();
        return ResponseEntity.created(uri).body(teacherSaved);
    }

    /**
     * POST  /api/professores/id : Excluir um professor.
     *
     * @param code : id do professor ser excluido
     * @return a ResponseEntity com status 201 (Criado)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> excluir(@PathVariable Long code) {
        log.info("Requisição REST para excluir Professor: {}", code);
        this.teacherService.deleteTeacher(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("professor.deletado", String.valueOf(code))).build();
    }

}
