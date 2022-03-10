package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.exceptions.InfraestruturaException;
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
        try{
            atualizarDisciplinas.execute();
            log.info("A estrutura inicial de disciplinas foi inicializada.");
        }
        catch (Exception e){
            throw new InfraestruturaException("Nao foi possivel buscar as disciplinas para estruturacao das tabelas");
        }
    }
    @PostConstruct
    public void construirFilas(){
        try{
            construtorDeFilas.construir();
            log.info("As filas foram construidas.");
        }
        catch (Exception e){
            throw new InfraestruturaException("Nao foi possivel construir as filas");
        }

    }


}
