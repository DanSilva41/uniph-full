package br.com.silva.uniph.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe base para entidades que conterão informações básicas
 *
 * @author Danilo Silva P.
 */
@MappedSuperclass
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(length = 20, nullable = false)
    private String nome;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(length = 40, nullable = false)
    private String sobrenome;

    @NotBlank
    @Email
    private String email;

    private String telefone;

    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.ORDINAL)
    private Genero genero;

    public String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    protected void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    protected void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    protected void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    protected void setGenero(Genero genero) {
        this.genero = genero;
    }
}

