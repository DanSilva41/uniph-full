package br.com.silva.uniph.procedures;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class ProcedureExecutor {

    private final EntityManager em;

    public ProcedureExecutor(EntityManager em) {
        this.em = em;
    }

    private StoredProcedureQuery montarProcedure(final String nomeProcedure, List<ProcedureParametro> procedureParametros) {
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery(nomeProcedure);
        procedureParametros.forEach(p -> storedProcedure.registerStoredProcedureParameter(p.getNome(), p.getTipo(), p.getModo()));
        return storedProcedure;
    }

    private void preencherParametros(StoredProcedureQuery storedProcedure, List<ProcedureParametroValor> valores) {
        valores.forEach(v -> storedProcedure.setParameter(v.getDescricao(), v.getValor()));
    }

    public List getResultList(String nomeProcedure, List<ProcedureParametro> procedureParametros, List<ProcedureParametroValor> procedureParametroValores) {
        StoredProcedureQuery storedProcedureQuery = montarProcedure(nomeProcedure, procedureParametros);
        preencherParametros(storedProcedureQuery, procedureParametroValores);
        return storedProcedureQuery.getResultList();
    }

}
