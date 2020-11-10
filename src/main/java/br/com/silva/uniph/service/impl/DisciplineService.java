package br.com.silva.uniph.service.impl;

import br.com.silva.uniph.domain.Discipline;
import br.com.silva.uniph.repository.DisciplineRepository;
import br.com.silva.uniph.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Class of service to manage disciplines
 *
 * @author Danilo Silva P.
 */
@Service
@Slf4j
@AllArgsConstructor
public class DisciplineService implements AbstractService<Discipline> {

    private final DisciplineRepository disciplineRepository;

    @Override
    public Optional<Discipline> findByCode(Long code) {
        return this.disciplineRepository.findById(code);
    }

    @Override
    public Collection<Discipline> findAll() {
        return this.disciplineRepository.findAll();
    }

    @Override
    public Discipline save(Discipline discipline) {
        return this.disciplineRepository.save(discipline);
    }

    /**
     * Delete discipline using the code
     *
     * @param code the discipline code to be deleted
     */
    @Override
    public void delete(Long code) {
        disciplineRepository.findById(code).ifPresent(disciplina -> {
            disciplineRepository.delete(disciplina);
            log.info("Discipline removed: {}", disciplina);
        });
    }

}
