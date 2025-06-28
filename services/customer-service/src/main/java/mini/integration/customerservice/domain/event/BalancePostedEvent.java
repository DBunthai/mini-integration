package mini.integration.customerservice.domain.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mini.integration.customerservice.lib.DomainEvent;

@Builder
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BalancePostedEvent implements DomainEvent {

    String customerId;
    String postedAmount;
    String registeredDate;

}
