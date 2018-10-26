package br.com.silva.uniph.domain;

/**
 * @author Danilo Silva P.
 */
public enum Genero {

    MASCULINO(1, "Masculino"), FEMININO(2, "Feminino");

    private final Integer id;
    private final String descricao;

    /**
     * Instancia um novo sexo.
     *
     * @param chave a chave
     * @param valor o valor
     */
    Genero(Integer chave, String valor) {
        id = chave;
        descricao = valor;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
