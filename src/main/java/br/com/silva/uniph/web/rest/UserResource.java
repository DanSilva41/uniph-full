package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.User;
import br.com.silva.uniph.service.UserService;
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
 * REST controller para gerenciar usuarios.
 * Esta classe acessa a entidade Usuario
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/manager/usuarios")
public class UserResource {

    @Autowired
    private UserService userService;

    /**
     * GET  /manager/usuarios : buscar todos os usuarios.
     *
     * @return status 200 (OK) e a lista de todos os usuarios
     */
    @GetMapping
    public Collection<User> listar() {
        log.info("REST request para buscar todos os usuarios");
        return this.userService.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<User> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Usuário: {}", codigo);
        Optional<User> usuarioRetornado = this.userService.findByCode(codigo);
        return usuarioRetornado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /manager/usuarios : Cria um novo usuário.
     *
     * @param user : o usuário a ser criado
     * @return a ResponseEntity com status 201 (Criado) e com o corpo do novo usuario
     */
    @PostMapping
    public ResponseEntity<User> cadastrar(@RequestBody @Valid User user) {
        log.info("Requisição REST para salvar Usuário: {}", user);
        User userSaved = this.userService.saveUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(userSaved.getCode()).toUri();
        return ResponseEntity.created(uri).body(userSaved);
    }

    /**
     * POST  /manager/usuarios/id : Excluir um usuário.
     *
     * @param code: code do usuário a ser excluido
     * @return a ResponseEntity com status 201 (Criado)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> excluir(@PathVariable Long code) {
        log.info("Requisição REST para excluir Usuário: {}", code);
        this.userService.deleteUser(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("usuario.deletado", String.valueOf(code))).build();
    }
}
