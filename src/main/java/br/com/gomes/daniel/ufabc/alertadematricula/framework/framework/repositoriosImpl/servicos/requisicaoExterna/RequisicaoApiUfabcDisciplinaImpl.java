package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.repositoriosImpl.servicos.requisicaoExterna;

import br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.DisciplinaVO;
import br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.excessoes.ChamadaDisciplinasIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.excessoes.ChamadaIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.excessoes.ChamadaVagasDisponiveisIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna.GetRecursoTexto;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna.RequisicaoApiUfabc;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.dominio.Disciplina;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RequisicaoApiUfabcDisciplinaImpl implements RequisicaoApiUfabc<List<Disciplina>> {

    @Autowired
    private GetRecursoTexto getRecursoTexto;

    public Optional<List<Disciplina>> requisitar() throws ChamadaIndisponivelException {
        try {
            Optional<String> line = getRecursoTexto.execute("https://matricula.ufabc.edu.br/cache/todasDisciplinas.js");
            line.orElseThrow(ChamadaVagasDisponiveisIndisponivelException::new);

            StringBuilder sb = new StringBuilder();
            sb.append(line.get());
            String everything = sb.deleteCharAt(sb.length() - 1).toString().replace("todasDisciplinas=[", "").replace(";", "");
            String[] dividido = everything.split("\\}\\,\\{\"campus");

            List<Disciplina> disciplinas = new ArrayList<>();
            for (String materia : dividido) {
                if (materia.charAt(0) == "\"".charAt(0)) {
                    materia = "{\"campus" + materia;

                }
                materia = materia + "}";
                ObjectMapper om = new ObjectMapper();
                DisciplinaVO disciplina = om.readValue(materia, DisciplinaVO.class);
                disciplinas.add(disciplina.toDomain());
            }

            return Optional.ofNullable(Optional.of(disciplinas).orElseThrow(ChamadaDisciplinasIndisponivelException::new));

        } catch (IOException e) {
            throw new ChamadaDisciplinasIndisponivelException();
        }
    }


}

