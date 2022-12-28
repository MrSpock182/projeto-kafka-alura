package br.com.alura.bytebank.messaging;

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
    private final KafkaTemplate<String, EnvioPix> kafkaTemplate;

    public ProdutorPixBancoCentral(
            @Value("${spring.kafka.topics.enviar-pagamento-banco-central}")
            String topic,
            KafkaTemplate<String, EnvioPix> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void envioMensagem(EnvioPix mensagem) {
        Message<EnvioPix> message = MessageBuilder.withPayload(mensagem)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.MESSAGE_KEY, mensagem.chaveProdutor())
                .build();
        kafkaTemplate.send(message).addCallback(futureCallback());
    }

    private ListenableFutureCallback<SendResult<String, EnvioPix>> futureCallback() {
        return new ListenableFutureCallback<SendResult<String, EnvioPix>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Falhou o envio para o kafka");
            }

            @Override
            public void onSuccess(SendResult<String, EnvioPix> result) {
                System.out.println("Envio realizado com sucesso");
            }
        };
    }

}
