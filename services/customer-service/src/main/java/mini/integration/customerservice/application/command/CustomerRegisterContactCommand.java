package mini.integration.customerservice.application.command;

import jakarta.validation.constraints.Email;
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
public class CustomerRegisterContactCommand {

    @Size(max = 50, message = "Phone number is exceeded 50 characters")
    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @Size(max = 320, message = "Email is exceeded 50 characters")
    @Email(message = "Invalid Email")
    @NotNull(message = "Email is required")
    private String email;

}
