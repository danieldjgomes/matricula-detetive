package br.com.gomes.daniel.ufabc.alertadematricula.app.repository;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ApiCaller {

    Optional<List<Disciplina>> getDisciplinas();

    Optional<Map<String, Integer>> getVagasDisponiveis();
}
