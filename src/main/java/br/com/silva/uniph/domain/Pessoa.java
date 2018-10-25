package br.com.silva.uniph.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tb_pessoa_cod_seq", sequenceName = "tb_pessoa_cod_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_pessoa_cod_seq")
    private Long codigo;

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
    private Sexo sexo;

    @OneToOne(mappedBy = "pessoa")
    private Usuario usuario;

    public Pessoa() {

    }

    public Pessoa(String nome, String sobrenome, String email, String telefone, LocalDate dataNascimento, Sexo sexo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;
        Pessoa pessoa = (Pessoa) otherObject;
        return Objects.equals(this.codigo, pessoa.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigo);
    }
}

