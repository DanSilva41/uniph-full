package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Teacher;
import br.com.silva.uniph.service.impl.TeacherService;
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
 * REST controller to manage teachers
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/teachers")
public class TeacherResource {

    @Autowired
    private TeacherService teacherService;

    /**
     * GET  /api/teachers : seek all teachers
     *
     * @return status 200 (OK) and the list of all teachers
     */
    @GetMapping
    public Collection<Teacher> findAll() {
        log.info("REST request to search for all teachers");
        return this.teacherService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Teacher> findByCode(@PathVariable Long code) {
        log.info("REST request to search for teachers: {}", code);
        Optional<Teacher> teacherReturned = this.teacherService.findByCode(code);
        return teacherReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/teachers : creates a new teacher
     *
     * @param teacher : the teacher to be created
     * @return ResponseEntity with status 201 (created) and with the body of the new teacher
     */
    @PostMapping
    public ResponseEntity<Teacher> save(@RequestBody @Valid Teacher teacher) {
        log.info("REST request to save trofessor: {}", teacher);
        Teacher teacherSaved = this.teacherService.save(teacher);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(teacherSaved.getCode()).toUri();
        return ResponseEntity.created(uri).body(teacherSaved);
    }

    /**
     * POST  /api/teachers/code : delete a teacher
     *
     * @param code : teacher code be deleted
     * @return ResponseEntity with status 201 (created)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        log.info("REST request to exclude teacher: {}", code);
        this.teacherService.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("teacher.removed", String.valueOf(code))).build();
    }

}
