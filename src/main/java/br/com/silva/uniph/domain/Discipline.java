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
@Table(schema = "domain", name = "discipline")
public class Discipline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "discipline_code", sequenceName = "discipline_code", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discipline_code")
    private Long code;

    @Size(min = 1, max = 100)
    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "workload", nullable = false)
    private Short workload;

    @ManyToOne
    @JoinColumn(name = "teacher_code", referencedColumnName = "code")
    private Teacher teacher;

    @ManyToMany(mappedBy = "disciplines", cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>(0);

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Short getWorkload() {
        return workload;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "codigo=" + code +
                ", descricao='" + description + '\'' +
                ", professor=" + teacher +
                ", cargaHoraria='" + workload + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equal(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
