package br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.excessoes;

public class ChamadaVagasDisponiveisIndisponivelException extends ChamadaIndisponivelException {
    public ChamadaVagasDisponiveisIndisponivelException() {
        super("Chamada de Vagas Disponiveis nao esta disponivel");
    }
}
