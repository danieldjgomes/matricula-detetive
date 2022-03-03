package br.com.gomes.daniel.ufabc.alertadematricula.app.useCasesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.AlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.MensagemAlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.ChamadaVagasDisponiveisIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.RepositorioDisciplinaIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.ApiCaller;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.mensagens.EnviarMensagemAlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.service.CallerService;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarVagasInteractor;
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

    private final ApiCaller apiCaller;
    private final DisciplinaRepository disciplinaRepository;
    private final CallerService callerService;
    //    private final EnviarMensagemAlteracaoVagaInteractor produzirMensagem;
    private EnviarMensagemAlteracaoVaga enviarMensagemAlteracaoVaga;

    @Inject
    public AtualizarVagasInteractorImpl(ApiCaller apiCaller, DisciplinaRepository disciplinaRepository, CallerService callerService, EnviarMensagemAlteracaoVaga enviarMensagemAlteracaoVaga) {
        this.apiCaller = apiCaller;
        this.disciplinaRepository = disciplinaRepository;
        this.callerService = callerService;
        this.enviarMensagemAlteracaoVaga = enviarMensagemAlteracaoVaga;
    }


    public void execute() {
        Optional<Map<String, Integer>> vagas = apiCaller.getVagasDisponiveis();
        vagas.orElseThrow(ChamadaVagasDisponiveisIndisponivelException::new);

        Optional<List<Disciplina>> disciplinas = disciplinaRepository.getDisciplinas();
        disciplinas.orElseThrow(RepositorioDisciplinaIndisponivelException::new);

        Map<String, Disciplina> identificadorDisciplinaMap = disciplinas.get().stream().collect(Collectors.toMap(Disciplina::getIdentificadorUFABC, Function.identity()));
        List<String> atualizados = new ArrayList<>();

        vagas.ifPresent(map -> map.forEach((id, vagasDisponiveis) -> {
                    if (callerService.isQuantidadeAtualizada(id, vagasDisponiveis, identificadorDisciplinaMap)) {
                        identificadorDisciplinaMap.get(id).setVagasDisponiveis(vagasDisponiveis);
                        atualizados.add(id);
//                        produzirMensagem.execute(new MensagemAlteracaoVaga(new AlteracaoVaga(identificadorDisciplinaMap.get(id))));
                        this.enviarMensagemAlteracaoVaga.execute(new MensagemAlteracaoVaga(new AlteracaoVaga(identificadorDisciplinaMap.get(id))));
                    }
                }
        ));

        if (!atualizados.isEmpty()) {
            disciplinaRepository.excluirDisciplinasInclusas(atualizados);
            disciplinaRepository.atualizarQuantidadeVagas(atualizados, identificadorDisciplinaMap);
        }
    }
}
