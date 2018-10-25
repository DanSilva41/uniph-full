package br.com.silva.uniph.service.dto;

import br.com.silva.uniph.domain.Aluno;
import br.com.silva.uniph.domain.Sexo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Uma DTO representa um aluno
 *
 * @author Danilo Silva P.
 */
@EqualsAndHashCode
@Builder
@ToString
public class AlunoDTO {

    private Long codigo;

    @NotNull
    @Size(min = 1, max = 20)
    private String nome;

    @NotNull
    @Size(min = 1, max = 40)
    private String sobrenome;

    @NotNull
    @Size(min = 1, max = 7)
    private String rg;

    @NotNull
    @CPF
    @Size(min = 1, max = 11)
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    private String telefone;

    @NotBlank
    @Email
    private String email;

    private Sexo sexo;

    public AlunoDTO() {
        // Empty constructor needed for Jackson.
    }

    public AlunoDTO(Aluno aluno) {
        this(aluno.getCodigo(), aluno.getNome(), aluno.getSobrenome(), aluno.getRg(), aluno.getCpf(), aluno.getDataNascimento(), aluno.getTelefone(), aluno.getEmail(), aluno.getSexo());
    }

    public AlunoDTO(Long codigo, String nome, String sobrenome, String rg, String cpf, LocalDate dataNascimento, String telefone, String email, Sexo sexo) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
