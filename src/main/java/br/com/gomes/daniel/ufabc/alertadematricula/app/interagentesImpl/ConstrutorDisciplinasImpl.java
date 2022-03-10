package br.com.gomes.daniel.ufabc.alertadematricula.app.interagentesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.interagentes.AtualizarDisciplinasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.construtor.ConstrutorDisciplinas;

import javax.inject.Named;

@Named
public class ConstrutorDisciplinasImpl implements ConstrutorDisciplinas {

    private final AtualizarDisciplinasInteractor atualizarDisciplinas;

    public ConstrutorDisciplinasImpl(AtualizarDisciplinasInteractor atualizarDisciplinas) {
        this.atualizarDisciplinas = atualizarDisciplinas;
    }

    public void construir() {
        atualizarDisciplinas.execute();
    }
}
