package br.com.alura.bancocentral.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ListenerPixBancoCentral {

    @KafkaListener(
            topics = "${spring.kafka.topics.enviar-pagamento-banco-central}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "envioPixFactory")
    public void listener(@Payload ConsumerRecord<String, BancoCentralRecebe> record) {
        BancoCentralRecebe value = record.value();
        System.out.println(value);
    }

}