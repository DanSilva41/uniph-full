package br.com.silva.uniph.domain;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Danilo Silva P.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_aluno")
public final class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tb_aluno_id_seq", sequenceName = "tb_aluno_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_aluno_id_seq")
    private Long codigo;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(length = 20, nullable = false)
    private String nome;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(length = 40, nullable = false)
    private String sobrenome;

    @NotNull
    @Size(min = 1, max = 7)
    @Column(length = 7, unique = true, nullable = false)
    private String rg;

    @NotNull
    @CPF
    @Size(min = 1, max = 11)
    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String telefone;

    @NotBlank
    @Email
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;

    @Override
    public String toString() {
        return "[ALUNO]:" + (codigo != null ? "\nCódigo = " + codigo + ";" : "")
                + (nome != null ? "\nNome = " + nome + ";" : "")
                + (sobrenome != null ? "\nSobrenome = " + sobrenome + ";" : "")
                + (rg != null ? "\nRG = " + rg + ";" : "") + (cpf != null ? "\nCPF = " + cpf + ";" : "")
                + (dataNascimento != null ? "\nDt de Nascimento = " + dataNascimento + ";" : "")
                + (telefone != null ? "\nTelefone = " + telefone + ";" : "")
                + (email != null ? "\nE-mail = " + email + ";" : "")
                + (sexo != null ? "\nSexo = " + sexo.getDescricao() + ";" : "");
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;
        Aluno aluno = (Aluno) otherObject;
        return Objects.equals(this.codigo, aluno.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigo);
    }
}
