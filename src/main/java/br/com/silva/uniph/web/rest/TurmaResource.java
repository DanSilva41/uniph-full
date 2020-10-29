package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Turma;
import br.com.silva.uniph.service.TurmaService;
import br.com.silva.uniph.web.rest.util.HeaderUtil;
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
 * REST controller para gerenciar turmas.
 * Esta classe acessa a entidade Turma
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/api/turmas")
public class TurmaResource {

    @Autowired
    private TurmaService turmaService;

    /**
     * GET  /api/turmas : buscar todos as turmas.
     *
     * @return status 200 (OK) e a lista de todas as turmas
     */
    @GetMapping
    public Collection<Turma> listar() {
        log.info("REST request para buscar todas as turmas");
        return this.turmaService.buscarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Turma> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Turma: {}", codigo);
        Optional<Turma> turmaRetornada = this.turmaService.buscarPorCodigo(codigo);
        return turmaRetornada.isPresent() ? ResponseEntity.ok(turmaRetornada.get()) : ResponseEntity.notFound().build();
    }

    /**
     * POST  /api/turmas : Cria uma nova turma.
     *
     * @param turma : a turma a ser criada
     * @return a ResponseEntity com status 201 (Criado) e com o corpo da nova turma
     * @throws URISyntaxException se a sintaxe do URI de localização estiver incorreta
     */
    @PostMapping
    public ResponseEntity<Turma> cadastrar(@RequestBody @Valid Turma turma) throws URISyntaxException {
        log.info("Requisição REST para salvar Turma: {}", turma);
        Turma turmaSalva = this.turmaService.salvar(turma);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(turmaSalva.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(turmaSalva);
    }

    /**
     * POST  /api/turmar/id : Excluir uma turma.
     *
     * @param id : id da turma ser excluida
     * @return a ResponseEntity com status 201 (Criado)
     */
    @PostMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        log.info("Requisição REST para excluir Turma: {}", id);
        this.turmaService.excluirTurma(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("turma.deletado", String.valueOf(id))).build();
    }

}
