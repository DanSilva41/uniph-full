package br.com.silva.uniph.domain;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Danilo Silva P.
 */
@Entity
@Builder
@Table(name = "tb_aluno")
@Inheritance(strategy = InheritanceType.JOINED)
public final class Aluno extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 7)
    @Column(length = 7, unique = true, nullable = false)
    private String rg;

    @NotNull
    @CPF
    @Size(min = 1, max = 11)
    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    public Aluno() {
        super();
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }


    @Override
    public String toString() {
        return "Aluno{" +
                "rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object otherObject) {
        return super.equals(otherObject);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
