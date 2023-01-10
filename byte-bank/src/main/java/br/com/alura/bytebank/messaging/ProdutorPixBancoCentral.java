package br.com.alura.bytebank.messaging;

import br.com.alura.bytebank.exception.InternalServerError;
import br.com.alura.bytebank.repository.ContaRepository;
import br.com.alura.bytebank.repository.orm.Conta;
import br.com.avro.PixAvro;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Configuration
public class ProdutorPixBancoCentral {

    private final String topic;
    private final ContaRepository repository;
    private final KafkaTemplate<String, PixAvro> kafkaTemplate;

    public ProdutorPixBancoCentral(
            @Value("${spring.kafka.topics.enviar-pagamento-banco-central}")
            String topic,
            ContaRepository repository,
            KafkaTemplate<String, PixAvro> kafkaTemplate) {
        this.topic = topic;
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviaMensagem(EnvioPix envioPix) {
        PixAvro pixAvro = PixAvro.newBuilder()
                .setChaveProdutor(envioPix.chaveProdutor())
                .setChaveQuemRecebe(envioPix.chaveQuemRecebe())
                .setValor(envioPix.valor())
                .build();
        Message<PixAvro> build = MessageBuilder.withPayload(pixAvro)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.MESSAGE_KEY, envioPix.chaveProdutor())
                .build();
        kafkaTemplate.send(build)
                .addCallback(futureCallback());
    }

    private ListenableFutureCallback<SendResult<String, PixAvro>> futureCallback() {
        return new ListenableFutureCallback<SendResult<String, PixAvro>>() {
            @Override
            public void onFailure(Throwable ex) {
                throw new InternalServerError("Pix indisponivel no momento tente mais tarde");
            }

            @Override
            public void onSuccess(SendResult<String, PixAvro> result) {
                PixAvro pixAvro = result.getProducerRecord().value();
                Conta conta = repository.procurarPelaChavePix(pixAvro.getChaveProdutor());
                repository.save(
                        new Conta(
                                conta.chavePix(),
                                conta.nomeCliente(),
                                conta.saldo().subtract(pixAvro.getValor())
                        )
                );
                System.out.println("Mensagem enviada");
            }
        };
    }

}
