package mini.integration.customerservice.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class CustomerRegisterCommand {

    @NotNull(message = "First Name is required")
    @Size(max = 100, message = "First Name length must be 1 to 100")
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Size(max = 100, message = "Last Name length must be 1 to 100")
    private String lastName;

    @NotNull(message = "Gender is required")
    private String gender;

    private ContactCommand contact;

    private AddressCommand address;
}
