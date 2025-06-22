package mini.integration.customerservice.domain.event;

import lombok.Builder;
import lombok.Value;
import mini.integration.customerservice.lib.DomainEvent;

@Builder
@Value
public class CustomerProfileEditedEvent implements DomainEvent {

    String id;

    String firstName;

    String lastName;

    String phoneNumber;

    String email;
}
