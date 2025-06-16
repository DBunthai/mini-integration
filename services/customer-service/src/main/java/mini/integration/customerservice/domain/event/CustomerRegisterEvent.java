package mini.integration.customerservice.domain.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Value;
import mini.integration.customerservice.lib.DomainEvent;

@Builder
@Value
public class CustomerRegisterEvent implements DomainEvent {

    String id;

    String firstName;

    String lastName;

    String phoneNumber;

    String email;
}
