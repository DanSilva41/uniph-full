package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
@Table(schema = "domain", name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "course_code", sequenceName = "course_code", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_code")
    private Long code;

    @Size(min = 1, max = 100)
    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "frequency_code", referencedColumnName = "code")
    private Frequency frequency;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(schema = "domain", name = "student_course", joinColumns = @JoinColumn(name = "course_code"), inverseJoinColumns = @JoinColumn(name = "student_code"))
    private Set<Student> students = new HashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(schema = "domain", name = "discipline_course", joinColumns = @JoinColumn(name = "course_code"), inverseJoinColumns = @JoinColumn(name = "discipline_code"))
    private Set<Discipline> disciplines = new HashSet<>(0);

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course that = (Course) o;
        return Objects.equal(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
