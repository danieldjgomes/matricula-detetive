package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.impl.useCasesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarDisciplinasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.InicializarDadosInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InicializarDadosInteractorImpl implements InicializarDadosInteractor {

    @Autowired
    private AtualizarDisciplinasInteractor atualizarDisciplinas;

    public void execute() {
        atualizarDisciplinas.execute();
    }
}
