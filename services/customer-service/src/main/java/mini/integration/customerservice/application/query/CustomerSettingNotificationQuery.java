package mini.integration.customerservice.application.query;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CustomerSettingNotificationQuery {

    @NotNull(message = "Customer Id is required")
    @org.hibernate.validator.constraints.UUID(message = "Customer Id is incorrect format")
    private String customerId;

    public java.util.UUID getCustomerId() {
        return java.util.UUID.fromString(customerId);
    }


}
