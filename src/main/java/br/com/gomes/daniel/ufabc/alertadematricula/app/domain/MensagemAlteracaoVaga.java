package br.com.gomes.daniel.ufabc.alertadematricula.app.domain;

import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;

public class MensagemAlteracaoVaga extends Mensagem<AlteracaoVaga> {

    public static final String FILA = "vagasAlteradas";

    public MensagemAlteracaoVaga(AlteracaoVaga conteudo) {
        super(conteudo, FILA);
    }
}
