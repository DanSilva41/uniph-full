package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Turma;
import br.com.silva.uniph.repository.TurmaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Classe de serviço para gerenciar turmas
 *
 * @author Danilo Silva P.
 */
@Service
@Transactional
@Slf4j
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Optional<Turma> buscarPorCodigo(Long codigo) {
        return this.turmaRepository.findById(codigo);
    }

    public Collection<Turma> buscarTodos() {
        return this.turmaRepository.findAll();
    }

    public Turma salvar(Turma turma) {
        return this.turmaRepository.save(turma);
    }

    /**
     * Excluir turma usando o código.
     *
     * @param codigo o codigo da turma a ser deletada.
     */
    public void excluirTurma(Long codigo) {
        turmaRepository.findById(codigo).ifPresent(turma -> {
            turmaRepository.delete(turma);
            log.info("Turma excluída: {}", turma);
        });
    }

}
