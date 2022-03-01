package br.com.gomes.daniel.ufabc.alertadematricula.framework.scheduler;

import br.com.gomes.daniel.ufabc.alertadematricula.app.useCases.AtualizarDisciplinasInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateListaDisciplinas {

    @Autowired
    private AtualizarDisciplinasInteractor atualizarDisciplinas;

    @EventListener(ApplicationReadyEvent.class)
    public void execute() {
        atualizarDisciplinas.execute();
    }
}
