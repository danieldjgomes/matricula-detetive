package br.com.gomes.daniel.ufabc.alertadematricula.domain.repositorio;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.dominio.Disciplina;

public interface DisciplinaRepository {

    void atualizarQuantidadeVagas(List<String> atualizados, Map<String, Disciplina> vagas);

    void atualizarQuantidadeDisciplinas(List<Disciplina> atualizados);

    void excluirDisciplinasInclusas(List<String> id);

    void excluirDisciplinasNaoInclusas(List<String> id);

    Optional<List<Disciplina>> getDisciplinas();

}
