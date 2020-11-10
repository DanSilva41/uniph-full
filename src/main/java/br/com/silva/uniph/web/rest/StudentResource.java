package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Student;
import br.com.silva.uniph.service.impl.StudentService;
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
 * REST controller to manage students
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/students")
public class StudentResource {

    @Autowired
    private StudentService studentService;

    /**
     * GET  /api/students : search for all students
     *
     * @return status 200 (OK) and the list of all students
     */
    @GetMapping
    public Collection<Student> findAll() {
        log.info("REST request to search for all students");
        return this.studentService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Student> findByCode(@PathVariable Long code) {
        log.info("REST request to pick up student: {}", code);
        Optional<Student> studentReturned = this.studentService.findByCode(code);
        return studentReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/students : creates a new student
     *
     * @param student : the student to be created
     * @return ResponseEntity with status 201 (Created) and with the body of the new student
     */
    @PostMapping
    public ResponseEntity<Student> save(@Valid @RequestBody Student student) {
        log.info("REST request to save animal: {}", student);
        Student studentSalved = this.studentService.save(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(studentSalved.getCpf()).toUri();
        return ResponseEntity.created(uri).body(studentSalved);
    }

    /**
     * POST  /api/students/code : delete a student
     *
     * @param code : student code to be deleted
     * @return ResponseEntity with status 201 (Created)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        log.info("REST request to exclude student: {}", code);
        this.studentService.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("student.removed", String.valueOf(code))).build();
    }
}
