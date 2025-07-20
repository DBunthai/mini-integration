package mini.integration.customerservice.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CustomerNotificationChannelConfigCommand {

    @org.hibernate.validator.constraints.UUID(message = "Customer Notification Config Id is incorrect format")
    private String customerNotificationConfigId;

    @org.hibernate.validator.constraints.UUID(message = "Customer Id is incorrect format")
    private String customerId;

    @NotNull(message = "Customer Notification Config Id is required")
    @JsonProperty("isEnabled")
    private boolean enabled;


    public UUID getCustomerNotificationConfigId() {
        return UUID.fromString(customerNotificationConfigId);
    }

    public UUID getCustomerId() {
        return java.util.UUID.fromString(customerId);
    }



}
