package br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions;

public class RepositorioDisciplinaIndisponivelException extends RepositorioIndisponivelException {

    public RepositorioDisciplinaIndisponivelException() {
        super(new RepositorioDisciplinaIndisponivelException());
    }
}
