package br.com.silva.uniph.service.impl;

import br.com.silva.uniph.domain.Teacher;
import br.com.silva.uniph.repository.TeacherRepository;
import br.com.silva.uniph.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Class of service to manage teachers
 *
 * @author Danilo Silva P.
 */
@Service
@Slf4j
@AllArgsConstructor
public class TeacherService implements AbstractService<Teacher> {

    private final TeacherRepository teacherRepository;

    @Override
    public Optional<Teacher> findByCode(Long code) {
        return this.teacherRepository.findById(code);
    }

    @Override
    public Collection<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Teacher save(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    /**
     * Delete teacher using the code
     *
     * @param code the student code to be deleted
     */
    @Override
    public void delete(Long code) {
        teacherRepository.findById(code).ifPresent(professor -> {
            teacherRepository.delete(professor);
            log.info("Teacher removed: {}", professor);
        });
    }
}
