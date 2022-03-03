package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.impl;

import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.AlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.domain.Mensagem;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repository.mensagens.EnviarMensagemAlteracaoVaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EnviarMensagemVagasAtualizadasVaga implements EnviarMensagemAlteracaoVaga {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void execute(Mensagem<AlteracaoVaga> alteracaoVagaMensagem) {
        this.rabbitTemplate.convertAndSend(alteracaoVagaMensagem.getFila(),alteracaoVagaMensagem.getConteudo().getDisciplina());
        log.info("A atualizacao da disciplina " + alteracaoVagaMensagem.getConteudo().getDisciplina().getNome() + "foi enviada para a fila " + alteracaoVagaMensagem.getFila());
    }
}
