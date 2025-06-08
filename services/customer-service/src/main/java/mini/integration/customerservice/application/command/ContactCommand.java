package mini.integration.customerservice.application.command;

import jakarta.validation.constraints.Email;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class ContactCommand {

    private String phoneNumber;

    @Email(message = "Invalid Email")
    private String email;

}
