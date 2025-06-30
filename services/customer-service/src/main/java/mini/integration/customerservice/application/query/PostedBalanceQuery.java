package mini.integration.customerservice.application.query;

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
public class PostedBalanceQuery {
    @NotNull(message = "Customer Id is required")
    @org.hibernate.validator.constraints.UUID(message = "Customer Id is incorrect format")
    private String customerId;

    public UUID getCustomerId() {
        return UUID.fromString(customerId);
    }
}
