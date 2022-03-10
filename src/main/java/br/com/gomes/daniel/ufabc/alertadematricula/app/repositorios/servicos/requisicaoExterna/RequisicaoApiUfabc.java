package br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaIndisponivelException;

import java.util.Optional;

public interface RequisicaoApiUfabc<R> extends RequisicaoExternaService {

    Optional<R> requisitar() throws ChamadaIndisponivelException;


}
