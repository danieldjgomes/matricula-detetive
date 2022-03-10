package br.com.gomes.daniel.ufabc.alertadematricula.app.dominio;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.dominio.Disciplina;

public class AlteracaoVaga {

    Disciplina disciplina;

    public AlteracaoVaga(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
}
