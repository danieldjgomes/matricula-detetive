package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.impl.useCasesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.ApiCaller;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarDisciplinasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AtualizarDisciplinasInteractorImpl implements AtualizarDisciplinasInteractor {

    @Autowired
    private ApiCaller apiCaller;

    @Autowired
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
