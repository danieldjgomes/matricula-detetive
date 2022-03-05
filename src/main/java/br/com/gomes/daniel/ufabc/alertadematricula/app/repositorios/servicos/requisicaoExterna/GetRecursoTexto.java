package br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna;

import java.io.IOException;
import java.util.Optional;

public interface GetRecursoTexto extends  RequisicaoExternaService{

    Optional<String> execute(String pathUrl) throws IOException;
}
