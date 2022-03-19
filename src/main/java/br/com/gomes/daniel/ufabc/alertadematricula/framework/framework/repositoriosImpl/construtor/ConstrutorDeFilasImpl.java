package br.com.gomes.daniel.ufabc.alertadematricula.framework.framework.repositoriosImpl.construtor;

import br.com.gomes.daniel.ufabc.alertadematricula.app.repositorios.construtor.mensageria.ConstrutorDeFilas;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstrutorDeFilasImpl implements ConstrutorDeFilas {

    @Value("${mensageria.rabbit.exchange}")
    private String NOME_EXCHANGE;

    @Value("${mensageria.rabbit.filas.vagas}")
    private String FILA_VAGAS;

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true,false,false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange((NOME_EXCHANGE));
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(),Binding.DestinationType.QUEUE,troca.getName(), fila.getName(),null);
    }

    public void construir(){
        Queue filaEstoque = this.fila(FILA_VAGAS);
        DirectExchange troca = this.trocaDireta();
        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(ligacaoEstoque);
    }


}
