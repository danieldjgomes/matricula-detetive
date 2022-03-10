package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.repositoriosImpl.servicos.SQL;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.sql.GeradorDeQuery;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.dominio.Disciplina;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class GeradorDeQueryImpl implements GeradorDeQuery {

    public String geraAtualizacaoVaga(List<String> atualizados, Map<String, Disciplina> vagas){
        StringBuilder query = new StringBuilder("INSERT IGNORE INTO disciplina (identificadorUFABC, nomeDisciplina, periodo, vagasDisponibilizadas, vagasDisponiveis, vagasIngressantes, creditos, codigo, campus) ");
        for(Map.Entry<String,Disciplina> vaga: vagas.entrySet()){
            if(atualizados.contains(vaga.getKey())){
                query.append(" SELECT '").append(vaga.getValue().getIdentificadorUFABC()).append("' identificadorUFABC, '").append(vaga.getValue().getNome()).append("' nomeDisciplina, '").append(vaga.getValue().getPeriodo()).append("' periodo, ").append(vaga.getValue().getVagasDisponibilizadas()).append(" vagasDisponibilizadas, ").append(vaga.getValue().getVagasDisponiveis()).append(" vagasDisponiveis, ").append(vaga.getValue().getVagasIngressantes()).append(" vagasIngressantes, ").append(vaga.getValue().getCreditos()).append(" creditos, '").append(vaga.getValue().getCodigo()).append("' codigo, '").append(vaga.getValue().getCampus()).append("' campus FROM disciplina UNION");
            }
        }
        return query.substring(0, query.length() - 5);
    }

    public String geraAtualizacaoVaga(List<Disciplina> atualizados){
        StringBuilder query = new StringBuilder("INSERT IGNORE INTO disciplina (identificadorUFABC, nomeDisciplina, periodo, vagasDisponibilizadas, vagasIngressantes, creditos, codigo, campus, vagasDisponiveis) ");
        for(Disciplina disciplina: atualizados){
            if(atualizados.contains(disciplina)){
                query.append(" SELECT '").append(disciplina.getIdentificadorUFABC()).append("' identificadorUFABC, '").append(disciplina.getNome()).append("' nomeDisciplina, '").append(disciplina.getPeriodo()).append("' periodo, ").append(disciplina.getVagasDisponibilizadas()).append(" vagasDisponibilizadas, ").append(disciplina.getVagasIngressantes()).append(" vagasIngressantes, ").append(disciplina.getCreditos()).append(" creditos, '").append(disciplina.getCodigo()).append("' codigo, '").append(disciplina.getCampus()).append("' campus, 9999 vagasDisponiveis  UNION");
            }
        }
        return query.substring(0, query.length() - 5);
    }
}
