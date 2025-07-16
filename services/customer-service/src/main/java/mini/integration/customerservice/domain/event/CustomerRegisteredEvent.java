package mini.integration.customerservice.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import mini.integration.customerservice.lib.DomainEvent;
import mini.integration.customerservice.lib.util.ObjectMapperLib;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerRegisteredEvent implements DomainEvent {

    String id;

    String firstName;

    String lastName;

    String phoneNumber;

    String email;

    @Override
    public String toString() {
        try {
            return ObjectMapperLib.objectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
