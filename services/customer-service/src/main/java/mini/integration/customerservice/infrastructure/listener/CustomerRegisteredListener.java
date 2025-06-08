package mini.integration.customerservice.infrastructure.listener;

import mini.integration.customerservice.domain.event.CustomerRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegisteredListener {

    @EventListener
    public void listening(CustomerRegisterEvent event) {
        // send to kafka
    }

}
