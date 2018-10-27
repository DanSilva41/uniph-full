package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Danilo Silva P.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_periodicidade")
public class Periodicidade implements Serializable {

    @Id
    @SequenceGenerator(name = "tb_periodicidade_cod_seq", sequenceName = "tb_periodicidade_cod_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_periodicidade_cod_seq")
    private Long codigo;

    @NotNull
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @NotNull
    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @ManyToMany
    @JoinTable(name = "tb_periodicidade_dias_semana", joinColumns = @JoinColumn(name = "codigo_periodicidade"), inverseJoinColumns = @JoinColumn(name = "codigo_dias_semana"))
    private List<DiasSemana> diasSemana;

    @OneToOne(mappedBy = "periodicidade")
    private Turma turma;

    public Long getCodigo() {
        return codigo;
    }

    public List<DiasSemana> getDiasSemana() {
        return Collections.unmodifiableList(diasSemana);
    }

    public Turma getTurma() {
        return turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodicidade that = (Periodicidade) o;
        return Objects.equal(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
