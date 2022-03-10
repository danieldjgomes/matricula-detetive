package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.repositoriosImpl.servicos.requisicaoExterna;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaDisciplinasIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna.GetRecursoTexto;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
public class GetRecursoTextoImpl implements GetRecursoTexto {

    public Optional<String> execute(String pathUrl) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(pathUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }catch (ConnectException e){
            log.error("O endpoint solicitado nao esta disponivel");
        }
        if (result.toString().isEmpty()){
            return Optional.empty();
        }
         return Optional.of(result.toString());
    }

}
