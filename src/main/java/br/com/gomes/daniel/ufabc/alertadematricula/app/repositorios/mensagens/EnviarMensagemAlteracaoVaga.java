package br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.mensagens;

import br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.AlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.Mensagem;

public interface EnviarMensagemAlteracaoVaga extends EnviarMensagem<Mensagem<AlteracaoVaga>> {

    public void execute(Mensagem<AlteracaoVaga> alteracaoVaga);

}
