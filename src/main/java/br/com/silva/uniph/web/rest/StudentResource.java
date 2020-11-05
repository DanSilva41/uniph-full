package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Student;
import br.com.silva.uniph.service.StudentService;
import br.com.silva.uniph.web.rest.util.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
public class StudentResource {

    @Autowired
    private StudentService studentService;

    /**
     * GET  /api/alunos : buscar todos os alunos.
     *
     * @return status 200 (OK) e a lista de todos os alunos
     */
    @GetMapping
    public Collection<Student> listar() {
        log.info("REST request para buscar todos os alunos");
        return this.studentService.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Student> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Aluno: {}", codigo);
        Optional<Student> alunoRetornado = this.studentService.findByCode(codigo);
        return alunoRetornado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/alunos : Cria um novo aluno.
     *
     * @param student : o aluno a ser criado
     * @return a ResponseEntity com status 201 (Criado) e com o corpo do novo aluno
     */
    @PostMapping
    public ResponseEntity<Student> cadastrar(@Valid @RequestBody Student student) {
        log.info("Requisição REST para salvar Animal: {}", student);
        Student studentSalved = this.studentService.saveStudent(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(studentSalved.getCpf()).toUri();
        return ResponseEntity.created(uri).body(studentSalved);
    }

    /**
     * POST  /api/alunos/id : Excluir um aluno.
     *
     * @param code : id do alunoa ser excluido
     * @return a ResponseEntity com status 201 (Criado)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> excluir(@PathVariable Long code) {
        log.info("Requisição REST para excluir Aluno: {}", code);
        this.studentService.deleteStudent(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("aluno.deletado", String.valueOf(code))).build();
    }
}
