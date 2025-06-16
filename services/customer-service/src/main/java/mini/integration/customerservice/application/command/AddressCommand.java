package mini.integration.customerservice.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressCommand {
    @Size(max = 100, message = "Line is exceeded 100 characters")
    @NotNull(message = "Line is required")
    private String line;

    @Size(max = 50, message = "City is exceeded 50 characters")
    @NotNull(message = "City is required")
    private String city;

    @Size(max = 50, message = "State is exceeded 50 characters")
    @NotNull(message = "State is required")
    private String state;

    @Size(max = 5, message = "ZipCode is exceeded 5 characters")
    @NotNull(message = "ZipCode is required")
    private String zipCode;
}
