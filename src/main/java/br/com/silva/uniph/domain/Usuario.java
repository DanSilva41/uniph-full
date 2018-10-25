package br.com.silva.uniph.domain;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Danilo Silva P.
 */
@Entity
@Builder
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tb_usuario_id_seq", sequenceName = "tb_usuario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_usuario_id_seq")
    private Long id;

    @NotNull
    @Size(min = 3)
    private String nomeCompleto;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Size(min = 5)
    @Column(unique = true, nullable = false)
    private String login;

    @NotNull
    @Size
    @Column(nullable = false)
    private String senha;

}
