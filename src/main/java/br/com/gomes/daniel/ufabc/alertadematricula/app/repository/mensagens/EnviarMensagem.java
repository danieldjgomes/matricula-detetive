package br.com.gomes.daniel.ufabc.alertadematricula.app.repository.mensagens;

public interface EnviarMensagem<M> {

    public void execute(M m);
}
