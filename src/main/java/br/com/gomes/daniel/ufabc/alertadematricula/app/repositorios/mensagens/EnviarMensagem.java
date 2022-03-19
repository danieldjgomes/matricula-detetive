package br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.mensagens;

public interface EnviarMensagem<M> {

    public void enviar(M m);
}
