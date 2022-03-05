package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.construtor.mensageria.ConstrutorDeFilas;
import br.com.gomes.daniel.ufabc.alertadematricula.app.interactor.AtualizarDisciplinasInteractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class EstruturadorDeProjeto {

    @Autowired
    private AtualizarDisciplinasInteractor atualizarDisciplinas;

    @Autowired
    private ConstrutorDeFilas construtorDeFilas;

    @PostConstruct
    public void construirDisciplinas() {
        atualizarDisciplinas.execute();
        log.info("A estrutura inicial de disciplinas foi inicializada.");

    }
    @PostConstruct
    public void construirFilas(){
        construtorDeFilas.construir();
        log.info("As filas foram construidas.");
    }


}
