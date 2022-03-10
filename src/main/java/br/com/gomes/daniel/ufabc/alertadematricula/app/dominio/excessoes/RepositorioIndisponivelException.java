package br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.excessoes;

public class RepositorioIndisponivelException extends InfraestruturaException {

    public RepositorioIndisponivelException(Object repositorio) {
        super("O repositorio nao se encontra disponivel: " + repositorio.getClass().getSimpleName());
    }
}
