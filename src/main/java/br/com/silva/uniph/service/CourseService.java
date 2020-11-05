package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Course;
import br.com.silva.uniph.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Class of service to manage courses
 *
 * @author Danilo Silva P.
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Optional<Course> findByCode(Long code) {
        return this.courseRepository.findById(code);
    }

    public Collection<Course> findAll() {
        return this.courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return this.courseRepository.save(course);
    }

    /**
     * Delete course using the code
     *
     * @param code the course code to be deleted
     */
    public void deleteCourse(Long code) {
        courseRepository.findById(code).ifPresent(turma -> {
            courseRepository.delete(turma);
            log.info("Course removed: {}", turma);
        });
    }

}
