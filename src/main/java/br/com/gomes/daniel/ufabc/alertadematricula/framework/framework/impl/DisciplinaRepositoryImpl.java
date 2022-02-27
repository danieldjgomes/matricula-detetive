package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.service.CallerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.domain.DAO.DisciplinaDAO;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.domain.exceptions.RepositorioDisciplinaIndisponivelException;

@Repository
public class DisciplinaRepositoryImpl implements DisciplinaRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CallerUtils utils;


    @Transactional
    public void atualizarQuantidadeVagas(List<String> atualizados, Map<String, Disciplina> vagas) {
        String codigo = utils.geraAtualizacaoVaga(atualizados,vagas);
        Query query = em.createNativeQuery(codigo);
        query.executeUpdate();
    }

    @Transactional
    public void atualizarQuantidadeDisciplinas(List<Disciplina> atualizados) {
        String codigo = utils.geraAtualizacaoVaga(atualizados);
        Query query = em.createNativeQuery(codigo);
        query.executeUpdate();
    }

    @Transactional
    public void excluirDisciplinaInclusa(List<String> id, boolean isInclusa) {
        String valoresIn = id.stream().map(String::valueOf).collect(Collectors.joining(","));
        String sql = "DELETE FROM DISCIPLINA WHERE identificadorUFABC " + (isInclusa ? "" : "NOT") + " IN ( " + valoresIn + " )";
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    public Boolean isQuantidadeAtualizada(Long identificadorUFABC, Integer quantidadeVagas) {
        String sql = "SELECT CASE WHEN vagasDisponiveis < ? THEN true " +
                "    ELSE false " +
                "    END AS RESULTADO FROM disciplina " +
                "    WHERE identificadorUFABC = ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, quantidadeVagas);
        query.setParameter(2, identificadorUFABC);
        List<BigInteger> result = (List<BigInteger>) query.getResultList();

        if(result.isEmpty())
            return false;

        return result.get(0).intValue() == 1;
    }

    @Override
    public Optional<List> getDisciplinas() {
        String sql = "SELECT * FROM DISCIPLINA";
        Query query = em.createNativeQuery(sql, DisciplinaDAO.class);
        List<DisciplinaDAO> disciplinasDAO = query.getResultList();
        List<Disciplina>  disciplinas = disciplinasDAO.stream().map(DisciplinaDAO::toDomain).collect(Collectors.toList());

         return Optional.ofNullable(Optional.of(disciplinas).orElseThrow(RepositorioDisciplinaIndisponivelException::new));

    }
    @Transactional
    public void queryRunner(String string) {
        Query query = em.createNativeQuery(string);
        query.executeUpdate();
    }

}

