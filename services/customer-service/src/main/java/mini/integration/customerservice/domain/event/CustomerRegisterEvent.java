package mini.integration.customerservice.domain.event;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import mini.integration.customerservice.lib.DomainEvent;

import java.util.UUID;

@Builder
@Value
public class CustomerRegisterEvent implements DomainEvent {

    String id;

    String firstName;

    String lastName;

    String phoneNumber;

    String email;
}
