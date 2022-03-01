package br.com.gomes.daniel.ufabc.alertadematricula.app.useCasesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.ApiCaller;
import br.com.gomes.daniel.ufabc.alertadematricula.app.service.CallerService;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarDisciplinasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AtualizarDisciplinasInteractorImpl implements AtualizarDisciplinasInteractor {

    private ApiCaller apiCaller;

    private DisciplinaRepository disciplinaRepository;

    public void execute() {
        Optional<List<Disciplina>> disciplinasAPI = apiCaller.getDisciplinas();
        if(disciplinasAPI.isPresent()){
            List<String> inclusos = new ArrayList<>();
            for (Disciplina disciplina : disciplinasAPI.get()) {
                inclusos.add((disciplina.getIdentificadorUFABC()));
            }
            disciplinaRepository.excluirDisciplinasNaoInclusas(inclusos);
            disciplinaRepository.atualizarQuantidadeDisciplinas(disciplinasAPI.get());
        }

    }
}
