package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Course;
import br.com.silva.uniph.service.impl.CourseService;
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
 * REST controller to manage courses.
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/courses")
public class CourseResource {

    @Autowired
    private CourseService courseService;

    /**
     * GET  /api/courses : pick up all courses
     *
     * @return status 200 (OK) and the list of all courses
     */
    @GetMapping
    public Collection<Course> findAll() {
        log.info("REST request to search all courses");
        return this.courseService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Course> findByCode(@PathVariable Long code) {
        log.info("REST request to search for courses: {}", code);
        Optional<Course> courseReturned = this.courseService.findByCode(code);
        return courseReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/courses : creates a new course
     *
     * @param course : the course to be created
     * @return ResponseEntity with status 201 (created) and with the body of the new course
     */
    @PostMapping
    public ResponseEntity<Course> save(@RequestBody @Valid Course course) {
        log.info("REST request to save course: {}", course);
        Course courseSaved = this.courseService.save(course);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(courseSaved.getCode()).toUri();
        return ResponseEntity.created(uri).body(courseSaved);
    }

    /**
     * POST  /api/course/code : Delete a course
     *
     * @param code : class code to be deleted
     * @return ResponseEntity with status 201 (created)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        log.info("REST request to exclude course: {}", code);
        this.courseService.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("course.removed", String.valueOf(code))).build();
    }

}
