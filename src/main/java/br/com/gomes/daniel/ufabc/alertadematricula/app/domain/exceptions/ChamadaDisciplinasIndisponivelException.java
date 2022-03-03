package br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions;

public class ChamadaDisciplinasIndisponivelException extends ChamadaIndisponivelException {

    public ChamadaDisciplinasIndisponivelException() {
        super("Chamada de Disciplinas nao esta disponivel");
    }
}