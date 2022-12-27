package br.com.alura.bytebank.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CriandoTopicoKafkaConfig {

    private final String enviarPagamentoBancoCentral;

    public CriandoTopicoKafkaConfig(
            @Value("${spring.kafka.topics.enviar-pagamento-banco-central}") final
            String enviarPagamentoBancoCentral) {
        this.enviarPagamentoBancoCentral = enviarPagamentoBancoCentral;
    }

    @Bean
    public NewTopic topicoEnviarPagamentoBancoCentral() {
        return TopicBuilder
                .name(enviarPagamentoBancoCentral)
                .partitions(3)
                .replicas(1)
                .compact()
                .configs(topicConfigs())
                .build();
    }

    private Map<String, String> topicConfigs() {
        String time = "86500000";
        Map<String, String> map = new HashMap<>();
        map.put("delete.retention.ms", time);
        return map;
    }

}
