package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.repositoriosImpl.servicos.requisicaoExterna;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaVagasDisponiveisIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.RepositorioDisciplinaIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna.GetRecursoTexto;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna.RequisicaoApiUfabc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class RequisicaoApiUfabcVagasDisponiveisImpl implements RequisicaoApiUfabc<Map<String, Integer>> {

    @Autowired
    private GetRecursoTexto getRecursoTexto;


    public Optional<Map<String, Integer>> requisitar() throws ChamadaIndisponivelException {
        try {
            Optional<String> line = getRecursoTexto.execute("https://matricula.ufabc.edu.br/cache/contagemMatriculas.js?1479842272");

            StringBuilder sb = new StringBuilder();
            sb.append(line);
            String everything = sb.deleteCharAt(sb.length() - 1).toString().replace("contagemMatriculas={", "").replace(";", "").replace("}", "");
            String[] dividido = everything.split(",");

            Map<String, Integer> vagas = new HashMap<>();
            for (String vaga : dividido) {
                String[] divisao = vaga.replace("\"", "").split(":");
                vagas.put(String.valueOf(divisao[0]), Integer.valueOf(divisao[1]));
            }
            return Optional.ofNullable(Optional.of(vagas).orElseThrow(ChamadaVagasDisponiveisIndisponivelException::new));

        } catch (IOException e) {
            throw new ChamadaVagasDisponiveisIndisponivelException();
        }
    }
}
