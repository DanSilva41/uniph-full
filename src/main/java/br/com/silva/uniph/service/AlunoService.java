package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Aluno;
import br.com.silva.uniph.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Classe de serviço para gerenciar alunos
 *
 * @author Danilo Silva P.
 */
@Service
@Transactional
@Slf4j
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Optional<Aluno> buscarPorCodigo(Long codigo) {
        return this.alunoRepository.findById(codigo);
    }

    public Collection<Aluno> buscarTodos() {
        return this.alunoRepository.findAll();
    }

    public Aluno salvarAluno(Aluno aluno) {
        return this.alunoRepository.save(aluno);
    }

    public Optional<Aluno> buscarPorCpf(String cpf) {
        return this.alunoRepository.findOneByCpf(cpf);
    }

    /**
     * Excluir aluno usando o código.
     *
     * @param codigo o codigo do aluno a ser deletado.
     */
    public void excluirAluno(Long codigo) {
        alunoRepository.findById(codigo).ifPresent(aluno -> {
            alunoRepository.delete(aluno);
            log.info("Aluno excluído: {}", aluno);
        });
    }

}
