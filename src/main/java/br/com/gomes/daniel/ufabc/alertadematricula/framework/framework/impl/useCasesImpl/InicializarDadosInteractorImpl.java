package br.com.gomes.daniel.ufabc.alertadematricula.app.useCasesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarDisciplinasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarVagasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.InicializarDadosInteractor;

public class InicializarDadosInteractorImpl implements InicializarDadosInteractor {

    private AtualizarDisciplinasInteractor atualizarDisciplinas;

    public void execute() {
        atualizarDisciplinas.execute();
    }
}
