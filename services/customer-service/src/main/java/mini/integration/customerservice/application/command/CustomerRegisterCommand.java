package mini.integration.customerservice.application.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerRegisterCommand {

    @NotNull(message = "First Name is required")
    @Size(max = 100, message = "First Name length must be 1 to 100")
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Size(max = 100, message = "Last Name length must be 1 to 100")
    private String lastName;

    @NotNull(message = "Gender is required")
    private String gender;

    @Valid
    @NotNull(message = "Contact is required")
    private CustomerRegisterContactCommand contact;

    @Valid
    @NotNull(message = "Address is required")
    private CustomerRegisterAddressCommand address;
}
