package mini.integration.customerservice.infrastructure.listener;

import mini.integration.customerservice.domain.event.CustomerRegisteredEvent;
import mini.integration.customerservice.lib.topic.CustomerEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegisteredListener {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CustomerRegisteredListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventListener
    public void listening(CustomerRegisteredEvent event) {

        // send to kafka
        kafkaTemplate.send(CustomerEvent.CUSTOMER_REGISTERED_EVENT, event.toString());
    }

}
