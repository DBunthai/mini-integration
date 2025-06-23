package mini.integration.customerservice.infrastructure.listener;

import mini.integration.customerservice.domain.event.CustomerProfileEditedEvent;
import mini.integration.customerservice.domain.event.CustomerRegisteredEvent;
import mini.integration.customerservice.lib.topic.CustomerEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerProfileEditedListener {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CustomerProfileEditedListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventListener
    public void listening(CustomerProfileEditedEvent event) {

        // send to kafka
        kafkaTemplate.send(CustomerEvent.CUSTOMER_PROFILE_EDITED_EVENT, event.toString());
    }

}
