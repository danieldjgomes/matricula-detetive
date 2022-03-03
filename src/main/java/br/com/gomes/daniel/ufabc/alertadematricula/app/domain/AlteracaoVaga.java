package br.com.gomes.daniel.ufabc.alertadematricula.app.domain;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

public class AlteracaoVaga {

    Disciplina disciplina;

    public AlteracaoVaga(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
}
