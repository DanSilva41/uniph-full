package br.com.silva.uniph.service.impl;

import br.com.silva.uniph.domain.Student;
import br.com.silva.uniph.repository.StudentRepository;
import br.com.silva.uniph.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Class of service to manage students
 *
 * @author Danilo Silva P.
 */
@Service
@Slf4j
@AllArgsConstructor
public class StudentService implements AbstractService<Student> {

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findByCode(Long code) {
        return this.studentRepository.findById(code);
    }

    @Override
    public Collection<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public Optional<Student> findByCpf(String cpf) {
        return this.studentRepository.findOneByCpf(cpf);
    }

    /**
     * Delete student using the code
     *
     * @param code the student code to be deleted
     */
    @Override
    public void delete(Long code) {
        studentRepository.findById(code).ifPresent(aluno -> {
            studentRepository.delete(aluno);
            log.info("Student removed: {}", aluno);
        });
    }

}
