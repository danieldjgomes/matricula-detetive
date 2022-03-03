package br.com.gomes.daniel.ufabc.alertadematricula.app.service;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaDisciplinasIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

@Service
public class CallerService {

    public Optional<String> requestToUrl(String pathUrl) throws IOException {
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
        return Optional.ofNullable(Optional.of(result.toString()).orElseThrow(ChamadaDisciplinasIndisponivelException::new));
    }

    public Boolean isQuantidadeAtualizada(String identificadorUFABC, Integer quantidadeVagas, Map<String, Disciplina> identificadorDisciplinaMap) {
        Disciplina disciplina = identificadorDisciplinaMap.get(identificadorUFABC);

        if (disciplina != null) {
            return quantidadeVagas < disciplina.getVagasDisponiveis();
        }
        return false;
    }


}
