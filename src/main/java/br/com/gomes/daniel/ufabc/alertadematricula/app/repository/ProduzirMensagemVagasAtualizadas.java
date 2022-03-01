package br.com.gomes.daniel.ufabc.alertadematricula.app.repository;

public interface MessageProducerVagasAtualizadas extends MessageProducer<T>{

    public <Disciplina> void execute(T t);
}
