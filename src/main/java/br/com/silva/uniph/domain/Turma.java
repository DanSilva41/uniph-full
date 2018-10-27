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
@Table(name = "tb_turma")
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tb_turma_cod_seq", sequenceName = "tb_turma_cod_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_turma_cod_seq")
    private Long codigo;

    @NotNull
    private String descricao;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "codigo_periodicidade")
    private Periodicidade periodicidade;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tb_aluno_turma", joinColumns = @JoinColumn(name = "codigo_turma"), inverseJoinColumns = @JoinColumn(name = "codigo_aluno"))
    private Set<Aluno> alunos = new HashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_disciplina_turma", joinColumns = @JoinColumn(name = "codigo_turma"), inverseJoinColumns = @JoinColumn(name = "codigo_disciplina"))
    private Set<Disciplina> disciplinas = new HashSet<>(0);

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Periodicidade getPeriodicidade() {
        return periodicidade;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma that = (Turma) o;
        return Objects.equal(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
