package br.com.gomes.daniel.ufabc.alertadematricula.app.interactorImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.AlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.MensagemAlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaVagasDisponiveisIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.RepositorioDisciplinaIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.interactor.AtualizarVagasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.mensagens.EnviarMensagemAlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.servicos.requisicaoExterna.RequisicaoApiUfabc;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
public class AtualizarVagasInteractorImpl implements AtualizarVagasInteractor {

    private final RequisicaoApiUfabc<Map<String, Integer>> requisicaoVagasDisponiveis;
    private final DisciplinaRepository disciplinaRepository;
    private final EnviarMensagemAlteracaoVaga enviarMensagemAlteracaoVaga;

    @Inject
    public AtualizarVagasInteractorImpl(RequisicaoApiUfabc<Map<String, Integer>> requisicaoVagasDisponiveis, DisciplinaRepository disciplinaRepository, EnviarMensagemAlteracaoVaga enviarMensagemAlteracaoVaga) {
        this.requisicaoVagasDisponiveis = requisicaoVagasDisponiveis;
        this.disciplinaRepository = disciplinaRepository;
        this.enviarMensagemAlteracaoVaga = enviarMensagemAlteracaoVaga;
    }

    public void execute() {
        Optional<Map<String, Integer>> vagas = requisicaoVagasDisponiveis.requisitar();
        vagas.orElseThrow(ChamadaVagasDisponiveisIndisponivelException::new);

        Optional<List<Disciplina>> disciplinas = disciplinaRepository.getDisciplinas();
        disciplinas.orElseThrow(RepositorioDisciplinaIndisponivelException::new);

        Map<String, Disciplina> identificadorDisciplinaMap = disciplinas.get().stream().collect(Collectors.toMap(Disciplina::getIdentificadorUFABC, Function.identity()));
        List<String> atualizados = new ArrayList<>();

        vagas.ifPresent(map -> map.forEach((id, vagasDisponiveis) -> {
                    if (isQuantidadeAtualizada(id, vagasDisponiveis, identificadorDisciplinaMap)) {
                        identificadorDisciplinaMap.get(id).setVagasDisponiveis(vagasDisponiveis);
                        atualizados.add(id);
                        this.enviarMensagemAlteracaoVaga.execute(new MensagemAlteracaoVaga(new AlteracaoVaga(identificadorDisciplinaMap.get(id))));
                    }
                }
        ));

        if (!atualizados.isEmpty()) {
            disciplinaRepository.excluirDisciplinasInclusas(atualizados);
            disciplinaRepository.atualizarQuantidadeVagas(atualizados, identificadorDisciplinaMap);
        }
    }


    public Boolean isQuantidadeAtualizada(String identificadorUFABC, Integer quantidadeVagas, Map<String, Disciplina> identificadorDisciplinaMap) {
        Disciplina disciplina = identificadorDisciplinaMap.get(identificadorUFABC);

        if (disciplina != null) {
            return quantidadeVagas < disciplina.getVagasDisponiveis();
        }
        return false;
    }
}
