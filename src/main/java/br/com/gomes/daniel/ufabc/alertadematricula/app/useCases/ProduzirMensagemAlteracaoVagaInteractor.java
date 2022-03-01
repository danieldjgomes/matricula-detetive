package br.com.gomes.daniel.ufabc.alertadematricula.app.useCases;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

public interface ProduzirMensagemAlteracaoVagaInteractor {

    public void execute(Disciplina disciplina);


}
