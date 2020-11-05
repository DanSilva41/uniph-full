package br.com.silva.uniph.domain;

/**
 * @author Danilo Silva P.
 */
public enum Gender {

    MALE(1, "Masculino"), FEMALE(2, "Feminino");

    private final Integer id;
    private final String description;

    /**
     * Instantiate a new gender.
     *
     * @param key   the key
     * @param value the value to key
     */
    Gender(Integer key, String value) {
        id = key;
        description = value;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
