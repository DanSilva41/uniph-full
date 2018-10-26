package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

    @Column(length = 5000)
    private String curriculo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo", referencedColumnName = "codigo_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "professor", targetEntity = Disciplina.class)
    private List<Disciplina> disciplina;

    public Professor(String nome, String sobrenome, String email, String telefone, LocalDate dataNascimento, Genero genero, String curriculo, Usuario usuario) {
        setNome(nome);
        setSobrenome(sobrenome);
        setEmail(email);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
        setGenero(genero);
        this.curriculo = curriculo;
        this.usuario = usuario;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Disciplina> getDisciplina() {
        return disciplina;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "codigo=" + codigo +
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
