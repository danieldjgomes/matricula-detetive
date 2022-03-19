package br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.excessoes;

public class RepositorioDisciplinaIndisponivelException extends RepositorioIndisponivelException {

    public RepositorioDisciplinaIndisponivelException() {
        super(new RepositorioDisciplinaIndisponivelException());
    }
}
