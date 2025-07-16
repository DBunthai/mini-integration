package mini.integration.customerservice.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class PostedBalanceCommand {

    @NotNull(message = "Customer Id is required")
    @UUID(message = "Customer Id is incorrect format")
    private String customerId;
    private BigDecimal postedAmount;
    private OffsetDateTime registeredDate;
}
