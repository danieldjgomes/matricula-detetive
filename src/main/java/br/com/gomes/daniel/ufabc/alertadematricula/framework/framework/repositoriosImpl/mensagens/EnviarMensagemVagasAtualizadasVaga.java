package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.repositoriosImpl.mensagens;

import br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.AlteracaoVaga;
import br.com.gomes.daniel.ufabc.alertadematricula.app.dominio.Mensagem;
import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.mensagens.EnviarMensagemAlteracaoVaga;
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
