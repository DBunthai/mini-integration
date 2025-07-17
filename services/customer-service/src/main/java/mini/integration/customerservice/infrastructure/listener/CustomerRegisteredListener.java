package mini.integration.customerservice.infrastructure.listener;

import mini.integration.customerservice.application.command.CustomerSettingAddCommand;
import mini.integration.customerservice.application.command.handler.CustomerSettingAddCommandHandler;
import mini.integration.customerservice.domain.event.CustomerRegisteredEvent;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.lib.topic.CustomerEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegisteredListener {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomerSettingAddCommandHandler customerSettingAddCommandHandler;

    public CustomerRegisteredListener(KafkaTemplate<String, String> kafkaTemplate, CustomerSettingAddCommandHandler customerSettingAddCommandHandler) {
        this.kafkaTemplate = kafkaTemplate;
        this.customerSettingAddCommandHandler = customerSettingAddCommandHandler;
    }

    @EventListener
    @Async
    public void kafkaPublisherListening(CustomerRegisteredEvent event) {

        // send to kafka
        kafkaTemplate.send(CustomerEvent.CUSTOMER_REGISTERED_EVENT, event.toString());
    }


    @EventListener
    public void customerSettingAddListening(CustomerRegisteredEvent event) throws GeneralException {

        CustomerSettingAddCommand command = CustomerSettingAddCommand.builder()
            .customerId(event.getId())
            .build();

        customerSettingAddCommandHandler.handle(command);

    }

}
