package br.com.silva.uniph.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Danilo Silva P.
 */
@Entity
@Table(schema = "security", name = "authority")
public final class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Authority authority = (Authority) obj;
        return !(name != null ? !name.equals(authority.name) : authority.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Authority{" + "nome='" + name + '\'' + "}";
    }
}
