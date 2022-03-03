package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.impl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.DisciplinaVO;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.ApiCaller;
import br.com.gomes.daniel.ufabc.alertadematricula.app.service.CallerService;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaVagasDisponiveisIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.RepositorioDisciplinaIndisponivelException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
@Service
@Slf4j
public class ApiCallerImpl implements ApiCaller {

    @Autowired
    private CallerService callerService;

    public Optional<List<Disciplina>> getDisciplinas() {
        try {
            Optional<String> line = callerService.requestToUrl("https://matricula.ufabc.edu.br/cache/todasDisciplinas.js");
            line.orElseThrow(ChamadaVagasDisponiveisIndisponivelException::new);

            StringBuilder sb = new StringBuilder();
            sb.append(line.get());
            String everything = sb.deleteCharAt(sb.length() - 1).toString().replace("todasDisciplinas=[", "").replace(";", "");
            String[] dividido = everything.split("\\}\\,\\{\"campus");

            List<Disciplina> disciplinas = new ArrayList<>();
            for (String materia : dividido) {
                if (materia.charAt(0) == "\"".charAt(0)) {
                    materia = "{\"campus"  + materia;

                }
                materia =   materia + "}";
                ObjectMapper om = new ObjectMapper();
                DisciplinaVO disciplina = om.readValue(materia, DisciplinaVO.class);
                disciplinas.add(disciplina.toDomain());
            }

            return Optional.ofNullable(Optional.of(disciplinas).orElseThrow(ChamadaVagasDisponiveisIndisponivelException::new));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, Integer>> getVagasDisponiveis() {
        try {
            Optional<String> line = callerService.requestToUrl("https://matricula.ufabc.edu.br/cache/contagemMatriculas.js?1479842272");

            StringBuilder sb = new StringBuilder();
            sb.append(line);
            String everything = sb.deleteCharAt(sb.length() - 1).toString().replace("contagemMatriculas={", "").replace(";", "").replace("}", "");
            String[] dividido = everything.split(",");

            Map<String, Integer> vagas = new HashMap<>();
            for (String vaga : dividido) {
                String[] divisao = vaga.replace("\"", "").split(":");
                vagas.put(String.valueOf(divisao[0]), Integer.valueOf(divisao[1]));
            }
            return Optional.ofNullable(Optional.of(vagas).orElseThrow(RepositorioDisciplinaIndisponivelException::new));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

