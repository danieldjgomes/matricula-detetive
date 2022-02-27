package br.com.gomes.daniel.ufabc.alertadematricula.app.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;


public interface CallerExterno{

    Optional<List<Disciplina>> getDisciplinas();

    Optional<Map<String,Integer>> getVagasDisponiveis();
}

