package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.Usuario;
import br.com.silva.uniph.service.UsuarioService;
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
 * REST controller para gerenciar usuarios.
 * Esta classe acessa a entidade Usuario
 *
 * @author Danilo Silva P.
 */
@Slf4j
@RestController
@RequestMapping("/manager/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * GET  /manager/usuarios : buscar todos os usuarios.
     *
     * @return status 200 (OK) e a lista de todos os usuarios
     */
    @GetMapping
    public Collection<Usuario> listar() {
        log.info("REST request para buscar todos os usuarios");
        return this.usuarioService.buscarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
        log.info("REST request para buscar Usuário: {}", codigo);
        Optional<Usuario> usuarioRetornado = this.usuarioService.buscarPorCodigo(codigo);
        return usuarioRetornado.isPresent() ? ResponseEntity.ok(usuarioRetornado.get()) : ResponseEntity.notFound().build();
    }

    /**
     * POST  /manager/usuarios : Cria um novo usuário.
     *
     * @param usuario : o usuário a ser criado
     * @return a ResponseEntity com status 201 (Criado) e com o corpo do novo usuario
     * @throws URISyntaxException se a sintaxe do URI de localização estiver incorreta
     */
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario) throws URISyntaxException {
        log.info("Requisição REST para salvar Usuário: {}", usuario);
        Usuario usuarioSalvo = this.usuarioService.salvar(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(usuarioSalvo.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(usuarioSalvo);
    }

    /**
     * POST  /manager/usuarios/id : Excluir um usuário.
     *
     * @param id : id do usuário a ser excluido
     * @return a ResponseEntity com status 201 (Criado)
     */
    @PostMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        log.info("Requisição REST para excluir Usuário: {}", id);
        this.usuarioService.excluirUsuario(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("usuario.deletado", String.valueOf(id))).build();
    }
}
