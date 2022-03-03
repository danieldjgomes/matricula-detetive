package br.com.gomes.daniel.ufabc.alertadematricula.app.useCasesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarDisciplinasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.InicializarDadosInteractor;

import javax.inject.Named;

@Named
public class InicializarDadosInteractorImpl implements InicializarDadosInteractor {

    private final AtualizarDisciplinasInteractor atualizarDisciplinas;

    public InicializarDadosInteractorImpl(AtualizarDisciplinasInteractor atualizarDisciplinas) {
        this.atualizarDisciplinas = atualizarDisciplinas;
    }

    public void execute() {
        atualizarDisciplinas.execute();
    }
}
