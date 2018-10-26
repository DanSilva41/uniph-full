package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Professor;
import br.com.silva.uniph.repository.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Classe de serviço para gerenciar professores
 *
 * @author Danilo Silva P.
 */
@Service
@Transactional
@Slf4j
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Optional<Professor> buscarPorCodigo(Long codigo) {
        return this.professorRepository.findById(codigo);
    }

    public Collection<Professor> buscarTodos() {
        return this.professorRepository.findAll();
    }

    public Professor salvar(Professor professor) {
        return this.professorRepository.save(professor);
    }

}
