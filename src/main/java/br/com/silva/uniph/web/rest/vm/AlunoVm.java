package br.com.silva.uniph.web.rest.vm;

import br.com.silva.uniph.domain.Genero;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * View Model para cadastrar usuários Fortcard.
 *
 * @author Danilo Silva P.
 */
@Value
@Builder
@JsonDeserialize(builder = AlunoVm.AlunoVmBuilder.class)
public class AlunoVm {

    private Long codigo;

    @Size(min = 1, max = 20)
    private String nome;

    @Size(min = 1, max = 40)
    private String sobrenome;

    @Email
    private String email;

    private String telefone;

    private LocalDate dataNascimento;

    private Genero genero;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class AlunoVmBuilder {

    }

}
