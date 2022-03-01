package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.impl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.ConstrutorDeQuery;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ConstrutorDeQueryImpl implements ConstrutorDeQuery {

    public String geraAtualizacaoVaga(List<String> atualizados, Map<String, Disciplina> vagas){
        String query = "INSERT IGNORE INTO disciplina (identificadorUFABC, nomeDisciplina, periodo, vagasDisponibilizadas, vagasDisponiveis, vagasIngressantes, creditos, codigo, campus) ";
        for(Map.Entry<String,Disciplina> vaga: vagas.entrySet()){
            if(atualizados.contains(vaga.getKey())){
                query += " SELECT '" + vaga.getValue().getIdentificadorUFABC() +"' identificadorUFABC, '" + vaga.getValue().getNome() + "' nomeDisciplina, '" + vaga.getValue().getPeriodo() + "' periodo, " + vaga.getValue().getVagasDisponibilizadas() + " vagasDisponibilizadas, " + vaga.getValue().getVagasDisponiveis() + " vagasDisponiveis, " + vaga.getValue().getVagasIngressantes() + " vagasIngressantes, " + vaga.getValue().getCreditos() + " creditos, '" + vaga.getValue().getCodigo() + "' codigo, '" + vaga.getValue().getCampus() + "' campus FROM disciplina UNION";
            }
        }
        return query.substring(0, query.length() - 5);
    }

    public String geraAtualizacaoVaga(List<Disciplina> atualizados){
        String query = "INSERT IGNORE INTO disciplina (identificadorUFABC, nomeDisciplina, periodo, vagasDisponibilizadas, vagasIngressantes, creditos, codigo, campus, vagasDisponiveis) ";
        for(Disciplina disciplina: atualizados){
            if(atualizados.contains(disciplina)){
                query += " SELECT '" + disciplina.getIdentificadorUFABC() +"' identificadorUFABC, '" + disciplina.getNome() + "' nomeDisciplina, '" + disciplina.getPeriodo() + "' periodo, " + disciplina.getVagasDisponibilizadas() + " vagasDisponibilizadas, "  + disciplina.getVagasIngressantes() + " vagasIngressantes, " + disciplina.getCreditos() + " creditos, '" + disciplina.getCodigo() + "' codigo, '" + disciplina.getCampus() + "' campus, 9999 vagasDisponiveis  UNION";
            }
        }
        return query.substring(0, query.length() - 5);
    }
}
