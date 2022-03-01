package br.com.gomes.daniel.ufabc.alertadematricula.app.useCasesImpl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.ApiCaller;
import br.com.gomes.daniel.ufabc.alertadematricula.app.service.CallerService;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarVagasInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.ProduzirMensagemAlteracaoVagaInteractor;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.domain.exceptions.ChamadaVagasDisponiveisIndisponivelException;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.domain.exceptions.RepositorioDisciplinaIndisponivelException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AtualizarVagasInteractorImpl implements AtualizarVagasInteractor {

   private ApiCaller apiCaller;
   private DisciplinaRepository disciplinaRepository;
   private CallerService callerService;
   private ProduzirMensagemAlteracaoVagaInteractor produzirMensagem;

    public void execute(){
        Optional<Map<String, Integer>> vagas = apiCaller.getVagasDisponiveis();
        vagas.orElseThrow(ChamadaVagasDisponiveisIndisponivelException::new);

        Optional<List<Disciplina>> disciplinas = disciplinaRepository.getDisciplinas();
        disciplinas.orElseThrow(RepositorioDisciplinaIndisponivelException::new);

        Map<String,Disciplina> identificadorDisciplinaMap = disciplinas.get().stream().collect(Collectors.toMap(Disciplina::getIdentificadorUFABC, Function.identity()));
        List<String> atualizados = new ArrayList<>();

        vagas.ifPresent(map -> map.forEach((id, vagasDisponiveis) -> {
                    if(callerService.isQuantidadeAtualizada(id,vagasDisponiveis, identificadorDisciplinaMap)){
                        identificadorDisciplinaMap.get(id).setVagasDisponiveis(vagasDisponiveis);
                        atualizados.add(id);
                        produzirMensagem.execute(identificadorDisciplinaMap.get(id));
                    }
                }
        ));

        if(!atualizados.isEmpty()){
            disciplinaRepository.excluirDisciplinasInclusas(atualizados);
            disciplinaRepository.atualizarQuantidadeVagas(atualizados, identificadorDisciplinaMap);
        }
    }
    }
