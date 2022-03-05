package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.repositoriosImpl.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.repositoriosImpl.servicos.SQL.GeradorDeQueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.domain.DAO.DisciplinaDAO;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.RepositorioDisciplinaIndisponivelException;

@Repository
public class DisciplinaRepositoryImpl implements DisciplinaRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private GeradorDeQueryImpl utils;

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
    public void excluirDisciplinasNaoInclusas(List<String> id) {
        String valoresIn = id.stream().map(String::valueOf).collect(Collectors.joining(","));
        String sql = "DELETE FROM DISCIPLINA WHERE identificadorUFABC NOT IN ( " + valoresIn + " )";
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Transactional
    public void excluirDisciplinasInclusas(List<String> id) {
        String valoresIn = id.stream().map(String::valueOf).collect(Collectors.joining(","));
        String sql = "DELETE FROM DISCIPLINA WHERE identificadorUFABC IN ( " + valoresIn + " )";
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Optional<List<Disciplina>> getDisciplinas() {
        String sql = "SELECT * FROM DISCIPLINA";
        Query query = em.createNativeQuery(sql, DisciplinaDAO.class);
        List<DisciplinaDAO> disciplinasDAO = query.getResultList();
        List<Disciplina>  disciplinas = disciplinasDAO.stream().map(DisciplinaDAO::toDomain).collect(Collectors.toList());

         return Optional.ofNullable(Optional.of(disciplinas).orElseThrow(RepositorioDisciplinaIndisponivelException::new));

    }

}

