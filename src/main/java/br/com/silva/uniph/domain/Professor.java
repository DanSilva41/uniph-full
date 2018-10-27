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
@Table(name = "tb_professor")
public class Professor extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tb_professor_cod_seq", sequenceName = "tb_professor_cod_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_professor_cod_seq")
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

    @Column(length = 5000)
    private String curriculo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo", referencedColumnName = "codigo_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "professor", targetEntity = Disciplina.class)
    private Set<Disciplina> disciplinas = new HashSet<>(0);

    public Professor(String nome, String sobrenome, String email, String telefone, LocalDate dataNascimento, Genero genero, String rg, String cpf, String curriculo, Usuario usuario) {
        setNome(nome);
        setSobrenome(sobrenome);
        setEmail(email);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
        setGenero(genero);
        this.rg = rg;
        this.cpf = cpf;
        this.curriculo = curriculo;
        this.usuario = usuario;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @Override
    public String toString() {
        return "Professor{" +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", curriculo='" + curriculo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equal(codigo, professor.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
