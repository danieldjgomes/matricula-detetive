package br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions;

public class ChamadaIndisponivelException extends InfraestruturaException {

    public ChamadaIndisponivelException(String mensagemDeErro) {
        super(mensagemDeErro);
    }
}
