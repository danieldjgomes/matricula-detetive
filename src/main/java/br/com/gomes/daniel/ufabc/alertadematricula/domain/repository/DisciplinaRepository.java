package br.com.gomes.daniel.ufabc.alertadematricula.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

public interface DisciplinaRepository {

    void atualizarQuantidadeVagas(List<String> atualizados, Map<String, Disciplina> vagas);

    void atualizarQuantidadeDisciplinas(List<Disciplina> atualizados);

    void excluirDisciplinaInclusa(List<String> id ,boolean isInclusa);

    Optional<List> getDisciplinas();

}
