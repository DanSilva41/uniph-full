package br.com.silva.uniph.security;

import br.com.silva.uniph.domain.Usuario;
import br.com.silva.uniph.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorLogin(login);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha incorretos"));
        return new User(login, usuario.getSenha(), getPermissoes(usuario));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> auhorities = new HashSet<>();
        usuario.getAuthorities().forEach(auth -> auhorities.add(new SimpleGrantedAuthority(auth.getNome().toUpperCase())));
        return auhorities;
    }
}
