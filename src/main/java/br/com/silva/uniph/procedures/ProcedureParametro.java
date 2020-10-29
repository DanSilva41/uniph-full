package br.com.silva.uniph.procedures;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.ParameterMode;

@AllArgsConstructor
@Getter
class ProcedureParametro {

    private String nome;
    private Class tipo;
    private ParameterMode modo;

}
