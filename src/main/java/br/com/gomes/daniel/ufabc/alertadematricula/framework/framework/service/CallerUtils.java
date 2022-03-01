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



}