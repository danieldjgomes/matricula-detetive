package br.com.gomes.daniel.ufabc.alertadematricula.app.useCasesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.ProduzirMensagemVagasAtualizadas;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.ProduzirMensagemAlteracaoVagaInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;

public class ProduzirMensagemAlteracaoVagaInteractorImpl implements ProduzirMensagemAlteracaoVagaInteractor {

    @Autowired
    private ProduzirMensagemVagasAtualizadas produzirMensagem;

    public void execute(Disciplina disciplina) {
        produzirMensagem.execute(disciplina);
    }
}
