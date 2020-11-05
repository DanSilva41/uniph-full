package br.com.silva.uniph.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Danilo Silva P.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "domain", name = "frequency")
public class Frequency implements Serializable {

    @Id
    @SequenceGenerator(name = "frequency_code", sequenceName = "frequency_code", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frequency_code")
    private Long code;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @ManyToMany
    @JoinTable(schema = "domain", name = "frequency_days_week", joinColumns = @JoinColumn(name = "frequency_code"), inverseJoinColumns = @JoinColumn(name = "days_week_code"))
    private List<DaysWeek> daysWeek;

    @OneToOne(mappedBy = "frequency")
    private Course course;

    public Long getCode() {
        return code;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public List<DaysWeek> getDaysWeek() {
        return daysWeek;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequency that = (Frequency) o;
        return Objects.equal(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
