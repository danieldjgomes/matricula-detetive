package br.com.gomes.daniel.ufabc.alertadematricula.framework.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.CallerExterno;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.domain.Disciplina;
import br.com.gomes.daniel.ufabc.alertadematricula.domain.repository.DisciplinaRepository;

@Component
public class UpdateListaDisciplinas {

    @Autowired
    CallerExterno callerExterno;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void execute() {
        Optional<List<Disciplina>> disciplinas = callerExterno.getDisciplinas();
        if(disciplinas.isPresent()) {
            List<String> inclusos = new ArrayList<>();

            for (Disciplina disciplina : disciplinas.get()) {
                inclusos.add((disciplina.getIdentificadorUFABC()));
            }
                disciplinaRepository.excluirDisciplinaInclusa(inclusos, false);
                disciplinaRepository.atualizarQuantidadeDisciplinas(disciplinas.get());
                System.out.println("Novas disciplinas atualizadas");
        }


    }
}
