package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Aluno;
import br.com.silva.uniph.service.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

/**
 * REST controller para gerenciar alunos.
 * Esta classe acessa a entidade Aluno
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    /**
     * GET  /api/alunos : buscar todos os alunos.
     *
     * @return status 200 (OK) e a lista de todos os alunos
     */
    @GetMapping
    public Collection<Aluno> listar() {
        log.info("REST request para buscar todos os alunos");
        return this.alunoService.buscarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Aluno: {}", codigo);
        Optional<Aluno> alunoRetornado = this.alunoService.buscarPorCodigo(codigo);
        return alunoRetornado.isPresent() ? ResponseEntity.ok(alunoRetornado.get()) : ResponseEntity.notFound().build();
    }

    /**
     * POST  /api/alunos : Cria um novo aluno.
     *
     * @param aluno : o aluno a ser criado
     * @return a ResponseEntity com status 201 (Criado) e com o corpo do novo aluno
     * @throws URISyntaxException se a sintaxe do URI de localização estiver incorreta
     */
    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody @Valid Aluno aluno) throws URISyntaxException {
        log.info("Requisição REST para salvar Animal: {}", aluno);
        Aluno alunoSalvo = this.alunoService.salvar(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(alunoSalvo.getCpf()).toUri();
        return ResponseEntity.created(uri).body(alunoSalvo);
    }


}
