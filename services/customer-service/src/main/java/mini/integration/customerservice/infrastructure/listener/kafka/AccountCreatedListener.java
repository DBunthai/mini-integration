package mini.integration.customerservice.infrastructure.listener.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AccountCreatedListener {

    @KafkaListener(topics = "accountcreated", groupId = "customer-group")
    void listening(String object) {
        System.out.println(object);
    }

}
