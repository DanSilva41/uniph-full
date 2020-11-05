package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Discipline;
import br.com.silva.uniph.repository.DisciplineRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Class of service to manage disciplines
 *
 * @author Danilo Silva P.
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public Optional<Discipline> findByCode(Long code) {
        return this.disciplineRepository.findById(code);
    }

    public Collection<Discipline> findAll() {
        return this.disciplineRepository.findAll();
    }

    public Discipline saveDiscipline(Discipline discipline) {
        return this.disciplineRepository.save(discipline);
    }

    /**
     * Delete discipline using the code
     *
     * @param code the discipline code to be deleted
     */
    public void deleteDiscipline(Long code) {
        disciplineRepository.findById(code).ifPresent(disciplina -> {
            disciplineRepository.delete(disciplina);
            log.info("Discipline removed: {}", disciplina);
        });
    }

}
