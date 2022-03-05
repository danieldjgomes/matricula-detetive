package br.com.gomes.daniel.ufabc.alertadematricula.app.interactorImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna.RequisicaoApiUfabc;
import br.com.gomes.daniel.ufabc.alertadematricula.app.interactor.AtualizarDisciplinasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
public class AtualizarDisciplinasInteractorImpl implements AtualizarDisciplinasInteractor {

    private final RequisicaoApiUfabc apiCaller;
    private final DisciplinaRepository disciplinaRepository;

    @Inject
    public AtualizarDisciplinasInteractorImpl(RequisicaoApiUfabc apiCaller, DisciplinaRepository disciplinaRepository) {
        this.apiCaller = apiCaller;
        this.disciplinaRepository = disciplinaRepository;
    }

    public void execute() {
        Optional<List<Disciplina>> disciplinasAPI = apiCaller.getDisciplinas();
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
