package br.com.silva.uniph.web.rest;

import br.com.silva.uniph.domain.User;
import br.com.silva.uniph.service.impl.UserService;
import br.com.silva.uniph.web.rest.util.HeaderUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

/**
 * REST controller to manage users
 *
 * @author Danilo Silva P.
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/manager/users")
public class UserResource {

    private UserService userService;

    /**
     * GET  /manager/users : search all users
     *
     * @return status 200 (OK) and the list of all users
     */
    @GetMapping
    public Collection<User> findAll() {
        log.info("REST request to search for all users");
        return this.userService.findAll();
    }

    /**
     * GET  /manager/users/code : search user by code
     *
     * @return status 200 (OK) and returns the found user
     */
    @GetMapping("/{code}")
    public ResponseEntity<User> findByCode(@PathVariable Long code) {
        log.info("REST request to search for User: {}", code);
        Optional<User> userReturned = this.userService.findByCode(code);
        return userReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /manager/users : creates a new user.
     *
     * @param user : the user to be created
     * @return ResponseEntity with status 201 (created) and with the body of the new user
     */
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid User user) {
        log.info("Requisição REST para salvar Usuário: {}", user);
        User userSaved = this.userService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(userSaved.getCode()).toUri();
        return ResponseEntity.created(uri).body(userSaved);
    }

    /**
     * POST  /manager/users/code : delete a user
     *
     * @param code: user code to be deleted
     * @return ResponseEntity with status 201 (created)
     */
    @PostMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        log.info("REST request to delete User: {}", code);
        this.userService.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("user.removed", String.valueOf(code))).build();
    }
}
