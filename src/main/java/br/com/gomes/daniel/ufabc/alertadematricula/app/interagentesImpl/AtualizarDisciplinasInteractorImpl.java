package br.com.gomes.daniel.ufabc.alertadematricula.app.interagentesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna.RequisicaoApiUfabc;
import br.com.gomes.daniel.ufabc.alertadematricula.app.interagentes.AtualizarDisciplinasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.dominio.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repositorio.DisciplinaRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
public class AtualizarDisciplinasInteractorImpl implements AtualizarDisciplinasInteractor {

    private final RequisicaoApiUfabc<List<Disciplina>> requisicaoDisciplinas;
    private final DisciplinaRepository disciplinaRepository;

    @Inject
    public AtualizarDisciplinasInteractorImpl(RequisicaoApiUfabc<List<Disciplina>> requisicaoDisciplinas, DisciplinaRepository disciplinaRepository) {
        this.requisicaoDisciplinas = requisicaoDisciplinas;
        this.disciplinaRepository = disciplinaRepository;
    }

    public void execute() {
        Optional<List<Disciplina>> disciplinasAPI = requisicaoDisciplinas.requisitar();
        if (disciplinasAPI.isPresent()) {
            List<String> inclusos = new ArrayList<>();
            for (Disciplina disciplina : disciplinasAPI.get()) {
                inclusos.add((disciplina.getIdentificadorUFABC()));
            }
            disciplinaRepository.excluirDisciplinasNaoInclusas(inclusos);
            disciplinaRepository.atualizarQuantidadeDisciplinas(disciplinasAPI.get());
        }

    }
}
