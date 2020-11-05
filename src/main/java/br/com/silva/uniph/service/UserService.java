package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.User;
import br.com.silva.uniph.repository.UserRepository;
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
@Transactional
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByCode(Long code) {
        return this.userRepository.findById(code);
    }

    public Collection<User> findAll() {
        return this.userRepository.findAll();
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Delete user using the code
     *
     * @param code the user code to be deleted
     */
    public void deleteUser(Long code) {
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
