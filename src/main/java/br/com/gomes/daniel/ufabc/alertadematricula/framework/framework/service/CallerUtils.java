package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

@Service
public class CallerUtils {

    public String requestAsString(String pathUrl) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(pathUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    public Boolean isQuantidadeAtualizada(String identificadorUFABC, Integer quantidadeVagas, Map<String, Disciplina> identificadorDisciplinaMap){
        Disciplina disciplina = identificadorDisciplinaMap.get(identificadorUFABC);

        if (disciplina != null){
            return quantidadeVagas < disciplina.getVagasDisponiveis();
        }
        return false;

    }

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
        String query = "INSERT IGNORE INTO disciplina (identificadorUFABC, nomeDisciplina, periodo, vagasDisponibilizadas, vagasIngressantes, creditos, codigo, campus) ";
        for(Disciplina disciplina: atualizados){
            if(atualizados.contains(disciplina)){
                query += " SELECT '" + disciplina.getIdentificadorUFABC() +"' identificadorUFABC, '" + disciplina.getNome() + "' nomeDisciplina, '" + disciplina.getPeriodo() + "' periodo, " + disciplina.getVagasDisponibilizadas() + " vagasDisponibilizadas, "  + disciplina.getVagasIngressantes() + " vagasIngressantes, " + disciplina.getCreditos() + " creditos, '" + disciplina.getCodigo() + "' codigo, '" + disciplina.getCampus() + "' campus  UNION";
            }
        }
        return query.substring(0, query.length() - 5);
    }


}
