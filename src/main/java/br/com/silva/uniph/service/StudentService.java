package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Student;
import br.com.silva.uniph.repository.StudentRepository;
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
@Transactional
@Slf4j
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Optional<Student> findByCode(Long code) {
        return this.studentRepository.findById(code);
    }

    public Collection<Student> findAll() {
        return this.studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Optional<Student> findByCpf(String cpf) {
        return this.studentRepository.findOneByCpf(cpf);
    }

    /**
     * Delete student using the code
     *
     * @param code the student code to be deleted
     */
    public void deleteStudent(Long code) {
        studentRepository.findById(code).ifPresent(aluno -> {
            studentRepository.delete(aluno);
            log.info("Student removed: {}", aluno);
        });
    }

}
