package br.com.gomes.daniel.ufabc.alertadematricula.app.repository;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

import java.util.List;
import java.util.Map;

public interface ConstrutorDeQuery {

    public String geraAtualizacaoVaga(List<String> atualizados, Map<String, Disciplina> vagas);

    public String geraAtualizacaoVaga(List<Disciplina> atualizados);

}
