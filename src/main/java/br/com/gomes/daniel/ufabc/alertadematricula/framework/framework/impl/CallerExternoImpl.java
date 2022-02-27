package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.DisciplinaVO;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.CallerExterno;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.domain.exceptions.ChamadaVagasDisponiveisIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.domain.exceptions.RepositorioDisciplinaIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.service.CallerUtils;

@Repository
public class CallerExternoImpl implements CallerExterno {

    @Autowired
    CallerUtils callerUtils;

    public Optional<List<Disciplina>> getDisciplinas() {

        try {
            String line = callerUtils.requestAsString("https://matricula.ufabc.edu.br/cache/todasDisciplinas.js");

            StringBuilder sb = new StringBuilder();
            sb.append(line);
            String everything = sb.deleteCharAt(sb.length() - 1).toString().replace("todasDisciplinas=[", "").replace(";", "");
//            String[] dividido = everything.split(",\\{\"t");
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
            String line = callerUtils.requestAsString("https://matricula.ufabc.edu.br/cache/contagemMatriculas.js?1479842272");

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



