package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Builder
@Table(name = "tb_dias_semana")
public class DiasSemana implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Byte codigo;

    @NotNull
    @Size(min = 5, max = 7)
    @Column(nullable = false)
    private String nome;

    public Byte getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "DiasSemana{" +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiasSemana that = (DiasSemana) o;
        return Objects.equal(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
