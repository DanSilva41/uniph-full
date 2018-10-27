package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@Table(name = "tb_disciplina")
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tb_disciplina_cod_seq", sequenceName = "tb_disciplina_cod_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_disciplina_cod_seq")
    private Long codigo;

    @NotNull
    private String descricao;

    @Column(name = "carga_horaria")
    private Short cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "codigo_professor", referencedColumnName = "codigo")
    private Professor professor;

    @ManyToMany(mappedBy = "disciplinas", cascade = CascadeType.ALL)
    private Set<Turma> turmas = new HashSet<>(0);

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Short getCargaHoraria() {
        return cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", professor=" + professor +
                ", cargaHoraria='" + cargaHoraria + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equal(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
