package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Danilo Silva P.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "domain", name = "student")
public final class Student extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "student_code", sequenceName = "student_code", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_code")
    private Long code;

    public Student(String nome, String sobrenome, String email, String telefone, LocalDate dataNascimento, Gender gender, String rg, String cpf, User user) {
        setFirstName(nome);
        setLastName(sobrenome);
        setEmail(email);
        setPhone(telefone);
        setDateBirth(dataNascimento);
        setGender(gender);
        setRg(rg);
        setCpf(cpf);
        setUsuario(user);
    }

    public Long getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome ='" + getFirstName() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equal(code, student.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
