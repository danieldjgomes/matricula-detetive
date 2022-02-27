package br.com.gomes.daniel.ufabc.alertadematricula.framework.domain.exceptions;

public class InfraestruturaException extends RuntimeException {

    public InfraestruturaException(String mensagemDeErro) {
        super(mensagemDeErro);
    }
}
