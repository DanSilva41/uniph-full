package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Discipline;
import br.com.silva.uniph.service.impl.DisciplineService;
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
 * REST controller to manage disciplines
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/disciplines")
public class DisciplineResource {

    @Autowired
    private DisciplineService disciplineService;

    /**
     * GET  /api/disciplines : search all disciplines
     *
     * @return status 200 (OK) and the list of all disciplines
     */
    @GetMapping
    public Collection<Discipline> findAll() {
        log.info("REST request to search all disciplines");
        return this.disciplineService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Discipline> findByCode(@PathVariable Long code) {
        log.info("REST request to search for discipline: {}", code);
        Optional<Discipline> disciplineReturned = this.disciplineService.findByCode(code);
        return disciplineReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/disciplines : creates a new discipline
     *
     * @param discipline : the discipline to be created
     * @return ResponseEntity with status 201 (Created) and with the body of the new discipline
     */
    @PostMapping
    public ResponseEntity<Discipline> save(@RequestBody @Valid Discipline discipline) {
        log.info("REST request to save Discipline: {}", discipline);
        Discipline disciplineSaved = this.disciplineService.save(discipline);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(disciplineSaved.getCode()).toUri();
        return ResponseEntity.created(uri).body(disciplineSaved);
    }

    /**
     * POST  /api/disciplines/code : delete a course
     *
     * @param code : discipline code to be deleted
     * @return ResponseEntity with status 201 (created)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        log.info("REST request to delete discipline: {}", code);
        this.disciplineService.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("discipline.removed", String.valueOf(code))).build();
    }

}
