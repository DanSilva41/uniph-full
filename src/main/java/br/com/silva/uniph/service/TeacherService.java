package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Teacher;
import br.com.silva.uniph.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Class of service to manage teachers
 *
 * @author Danilo Silva P.
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Optional<Teacher> findByCode(Long code) {
        return this.teacherRepository.findById(code);
    }

    public Collection<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    public Teacher saveTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    /**
     * Delete teacher using the code
     *
     * @param code the student code to be deleted
     */
    public void deleteTeacher(Long code) {
        teacherRepository.findById(code).ifPresent(professor -> {
            teacherRepository.delete(professor);
            log.info("Teacher removed: {}", professor);
        });
    }
}
