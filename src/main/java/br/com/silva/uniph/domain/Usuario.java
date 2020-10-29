package br.com.silva.uniph.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Danilo Silva P.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tb_usuario_cod_seq", sequenceName = "tb_usuario_cod_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_usuario_cod_seq")
    private Long codigo;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(unique = true, nullable = false)
    private String login;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(nullable = false)
    private String senha;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_authority", joinColumns = {
            @JoinColumn(name = "usuario_codigo", referencedColumnName = "codigo")}, inverseJoinColumns = {
            @JoinColumn(name = "authority_nome", referencedColumnName = "nome")})
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>(0);

    public Long getCodigo() {
        return codigo;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equal(codigo, usuario.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
