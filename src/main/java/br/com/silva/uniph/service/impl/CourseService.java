package br.com.silva.uniph.service.impl;

import br.com.silva.uniph.domain.Course;
import br.com.silva.uniph.repository.CourseRepository;
import br.com.silva.uniph.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Class of service to manage courses
 *
 * @author Danilo Silva P.
 */
@Service
@Slf4j
@AllArgsConstructor
public class CourseService implements AbstractService<Course> {

    private final CourseRepository courseRepository;

    @Override
    public Optional<Course> findByCode(Long code) {
        return this.courseRepository.findById(code);
    }

    @Override
    public Collection<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Course save(Course course) {
        return this.courseRepository.save(course);
    }

    /**
     * Delete course using the code
     *
     * @param code the course code to be deleted
     */
    @Override
    public void delete(Long code) {
        courseRepository.findById(code).ifPresent(turma -> {
            courseRepository.delete(turma);
            log.info("Course removed: {}", turma);
        });
    }

}
