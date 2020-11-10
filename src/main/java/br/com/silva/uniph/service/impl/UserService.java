package br.com.silva.uniph.service.impl;

import br.com.silva.uniph.domain.User;
import br.com.silva.uniph.repository.UserRepository;
import br.com.silva.uniph.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Class of service to manage users
 *
 * @author Danilo Silva P.
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserService implements AbstractService<User> {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByCode(Long code) {
        return this.userRepository.findById(code);
    }

    @Override
    public Collection<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Delete user using the code
     *
     * @param code the user code to be deleted
     */
    @Override
    public void delete(Long code) {
        userRepository.findById(code).ifPresent(usuario -> {
            userRepository.delete(usuario);
            log.info("User removed: {}", usuario);
        });
    }

    public Optional<User> buscarPorLogin(String login) {
        return this.userRepository.findOneByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(Long codigo) {
        return userRepository.findOneWithAuthoritiesByCode(codigo);
    }
}
