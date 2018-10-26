package br.com.silva.uniph.service;

import br.com.silva.uniph.domain.Usuario;
import br.com.silva.uniph.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Classe de serviço para gerenciar usuarios
 *
 * @author Danilo Silva P.
 */
@Service
@Transactional
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> buscarPorCodigo(Long codigo) {
        return this.usuarioRepository.findById(codigo);
    }

    public Collection<Usuario> buscarTodos() {
        return this.usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

}
