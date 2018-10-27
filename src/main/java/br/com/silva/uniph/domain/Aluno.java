package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Danilo Silva P.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_aluno")
public final class Aluno extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tb_aluno_cod_seq", sequenceName = "tb_aluno_cod_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_aluno_cod_seq")
    private Long codigo;

    @NotNull
    @Size(min = 1, max = 7)
    @Column(length = 7, unique = true, nullable = false)
    private String rg;

    @NotNull
    @CPF
    @Size(min = 1, max = 11)
    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo", referencedColumnName = "codigo_usuario")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "alunos", cascade = CascadeType.ALL)
    private Set<Turma> turmas = new HashSet<>(0);

    public Aluno(String nome, String sobrenome, String email, String telefone, LocalDate dataNascimento, Genero genero, String rg, String cpf, Usuario usuario) {
        setNome(nome);
        setSobrenome(sobrenome);
        setEmail(email);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
        setGenero(genero);
        this.rg = rg;
        this.cpf = cpf;
        this.usuario = usuario;
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome ='" + getNome() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equal(codigo, aluno.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
