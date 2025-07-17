package mini.integration.customerservice.config;

import mini.integration.customerservice.lib.util.ObjectMapperLib;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaConsumerFactoryCustomizer;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig implements DefaultKafkaProducerFactoryCustomizer, DefaultKafkaConsumerFactoryCustomizer {

    @Override
    public void customize(DefaultKafkaProducerFactory<?, ?> producerFactory) {
        producerFactory.setValueSerializer(new JsonSerializer<>(ObjectMapperLib.objectMapper(ObjectMapperLib.ObjectMapperRule.RESTRICTED)));
    }


    @Override
    public void customize(DefaultKafkaConsumerFactory<?, ?> consumerFactory) {
        consumerFactory.setValueDeserializer(new JsonDeserializer<>(ObjectMapperLib.objectMapper()));
    }
}
