package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Disciplina;
import br.com.silva.uniph.repository.DisciplinaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Classe de serviço para gerenciar disciplinas
 *
 * @author Danilo Silva P.
 */
@Service
@Transactional
@Slf4j
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Optional<Disciplina> buscarPorCodigo(Long codigo) {
        return this.disciplinaRepository.findById(codigo);
    }

    public Collection<Disciplina> buscarTodos() {
        return this.disciplinaRepository.findAll();
    }

    public Disciplina salvar(Disciplina disciplina) {
        return this.disciplinaRepository.save(disciplina);
    }

    /**
     * Excluir disciplina usando o código.
     *
     * @param codigo o codigo da disciplina a ser deletada.
     */
    public void excluirDisciplina(Long codigo) {
        disciplinaRepository.findById(codigo).ifPresent(disciplina -> {
            disciplinaRepository.delete(disciplina);
            log.info("Disciplina excluída: {}", disciplina);
        });
    }

}
