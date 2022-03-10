package br.com.gomes.daniel.ufabc.alertadematricula.framework.agendador;

import br.com.gomes.daniel.ufabc.alertadematricula.app.interagentes.AtualizarVagasInteractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class UpdateVagasDisponiveis {

    @Autowired
    private AtualizarVagasInteractor atualizarVagas;



    @Scheduled(cron = "*/10 * * * * *")
    public void execute() {
        atualizarVagas.execute();
    }


}
