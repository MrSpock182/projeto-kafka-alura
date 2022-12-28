package br.com.alura.bancocentral.configuration;

import br.com.alura.bancocentral.messaging.BancoCentralRecebe;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BancoCentralRecebe> envioPixFactory(
            KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, BancoCentralRecebe> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }

    private ConsumerFactory<String, BancoCentralRecebe> consumerFactory(KafkaProperties kafkaProperties) {
        DefaultJackson2JavaTypeMapper mapper = new DefaultJackson2JavaTypeMapper();
        mapper.addTrustedPackages("*");
        JsonDeserializer<BancoCentralRecebe> deserializer = new JsonDeserializer<>(BancoCentralRecebe.class);
        deserializer.setTypeMapper(mapper);
        deserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(),
                new StringDeserializer(),
                deserializer
        );
    }

}
