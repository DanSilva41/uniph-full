package br.com.silva.uniph.domain;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Base class for personal entities that will contain basic information
 *
 * @author Danilo Silva P.
 */
@MappedSuperclass
public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 30)
    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Column(name = "date_birth", nullable = false)
    private LocalDate dateBirth;

    @NotBlank
    @Email
    @Size(min = 1, max = 100)
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Size(min = 1, max = 20)
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @NotBlank
    @Size(min = 1, max = 7)
    @Column(length = 7, unique = true, nullable = false)
    private String rg;

    @NotBlank
    @CPF
    @Size(min = 1, max = 11)
    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", referencedColumnName = "user_code")
    private User user;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public User getUsuario() {
        return user;
    }

    public void setUsuario(User user) {
        this.user = user;
    }
}

