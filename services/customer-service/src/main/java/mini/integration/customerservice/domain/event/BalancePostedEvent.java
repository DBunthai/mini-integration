package mini.integration.customerservice.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mini.integration.customerservice.lib.DomainEvent;
import mini.integration.customerservice.lib.util.ObjectMapperLib;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BalancePostedEvent implements DomainEvent {

    UUID customerId;
    BigDecimal postedAmount;
    OffsetDateTime registeredDate;

    @Override
    public String toString() {
        try {
            return ObjectMapperLib.objectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
