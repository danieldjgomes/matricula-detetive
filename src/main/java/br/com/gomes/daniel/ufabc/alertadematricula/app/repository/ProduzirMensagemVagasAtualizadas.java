package br.com.gomes.daniel.ufabc.alertadematricula.app.repository;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

public interface ProduzirMensagemVagasAtualizadas {

    public void execute(Disciplina disciplina);
}
