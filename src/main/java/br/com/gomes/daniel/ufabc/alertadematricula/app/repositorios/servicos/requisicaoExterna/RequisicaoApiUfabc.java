package br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RequisicaoApiUfabc extends RequisicaoExternaService {

    Optional<List<Disciplina>> getDisciplinas();

    Optional<Map<String, Integer>> getVagasDisponiveis();


}
