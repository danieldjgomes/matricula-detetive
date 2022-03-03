package br.com.gomes.daniel.ufabc.alertadematricula.app.domain;

public class Mensagem<M> {

    M conteudo;
    String fila;

    public Mensagem(M conteudo, String fila) {
        this.conteudo = conteudo;
        this.fila = fila;
    }

    public M getConteudo(){
        return this.conteudo;
    }

    public String getFila() {
        return fila;
    }
}
