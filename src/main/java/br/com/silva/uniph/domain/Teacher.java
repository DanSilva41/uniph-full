package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Table(schema = "domain", name = "teacher")
public class Teacher extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "teacher_code", sequenceName = "teacher_code", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_code")
    private Long code;

    @Column(name = "biography", length = 5000)
    private String biography;

    @OneToMany(mappedBy = "teacher", targetEntity = Discipline.class)
    private Set<Discipline> disciplines = new HashSet<>(0);

    public Teacher(String nome, String sobrenome, String email, String telefone, LocalDate dataNascimento, Gender gender, String rg, String cpf, String biography, User user) {
        setFirstName(nome);
        setLastName(sobrenome);
        setEmail(email);
        setPhone(telefone);
        setDateBirth(dataNascimento);
        setGender(gender);
        setRg(rg);
        setCpf(cpf);
        setUsuario(user);
        this.biography = biography;
    }

    public Long getCode() {
        return code;
    }

    public String getBiography() {
        return biography;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "biography='" + biography + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equal(code, teacher.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
