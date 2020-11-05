package br.com.silva.uniph.service.dto;

import br.com.silva.uniph.domain.Student;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO represents a student
 *
 * @author Danilo Silva P.
 */
@EqualsAndHashCode
@Builder
@ToString
public class StudentDTO {

    @NotNull
    @Size(min = 1, max = 7)
    private String rg;

    @NotNull
    @CPF
    @Size(min = 1, max = 11)
    private String cpf;

    public StudentDTO() {
        // Empty constructor needed for Jackson.
    }

    public StudentDTO(Student student) {
        this(student.getRg(), student.getCpf());
    }

    public StudentDTO(@NotNull String rg, @NotNull String cpf) {
        this.rg = rg;
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
