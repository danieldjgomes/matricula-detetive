package br.com.gomes.daniel.ufabc.alertadematricula.framework.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.CallerExterno;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;
import br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.service.CallerUtils;

@Component
@EnableScheduling
public class UpdateVagasDisponiveis {

    @Autowired
    CallerExterno callerExterno;

    @Autowired
    CallerUtils utils;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    final SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Scheduled(cron = "0 * * * * *")
    public void execute() {
        Optional<Map<String, Integer>> vagas = callerExterno.getVagasDisponiveis();
        List<Disciplina> disciplinas = disciplinaRepository.getDisciplinas().get();
        Map<String,Disciplina> identificadorDisciplinaMap = disciplinas.stream().collect(Collectors.toMap(Disciplina::getIdentificadorUFABC, Function.identity()));
        List<String> atualizados = new ArrayList<>();

        vagas.ifPresent(map -> map.forEach((id, vagasDisponiveis) -> {

            if(utils.isQuantidadeAtualizada(id,vagasDisponiveis, identificadorDisciplinaMap)){

                identificadorDisciplinaMap.get(id).setVagasDisponiveis(vagasDisponiveis);
                atualizados.add(id);
                System.out.println(formatoData.format(new Date()) + "---"  + identificadorDisciplinaMap.get(id).toString());

                if(id.equals("716")){
                    System.out.println(new Date() + " Disponivel Projeto Dirigido B1-Matutino (Santo André) " + id + ":" + vagasDisponiveis);
                }
            }
        }
    ));
        if(!atualizados.isEmpty()){
            disciplinaRepository.excluirDisciplinaInclusa(atualizados,true);
            disciplinaRepository.atualizarQuantidadeVagas(atualizados, identificadorDisciplinaMap);
        }
    }


}
