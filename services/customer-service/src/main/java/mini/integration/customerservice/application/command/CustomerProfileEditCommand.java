package mini.integration.customerservice.application.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.util.Optional;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerProfileEditCommand {

    @NotNull(message = "Id is required")
    @UUID(message = "Id is incorrect format")
    private String id;

    @Builder.Default
    @Size(max = 100, message = "First Name length must be 1 to 100")
    private Optional<String> firstName = Optional.empty();

    @Builder.Default
    @Size(max = 100, message = "Last Name length must be 1 to 100")
    private Optional<String> lastName = Optional.empty();

    @Builder.Default
    private Optional<String> gender = Optional.empty();

    @Builder.Default
    private Optional<CustomerEditContactCommand> contactCommand = Optional.empty();

    @Builder.Default
    private Optional<CustomerEditAddressCommand> addressCommand = Optional.empty();


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CustomerEditContactCommand {

        @Builder.Default
        @Size(max = 50, message = "Phone number is exceeded 50 characters")
        private Optional<String> phoneNumber = Optional.empty();

        @Builder.Default
        @Size(max = 320, message = "Email is exceeded 50 characters")
        @Email(message = "Invalid Email")
        private Optional<String> email = Optional.empty();

    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CustomerEditAddressCommand {

        @Builder.Default
        @Size(max = 100, message = "Line is exceeded 100 characters")
        private Optional<String> line = Optional.empty();

        @Builder.Default
        @Size(max = 50, message = "City is exceeded 50 characters")
        private Optional<String> city = Optional.empty();

        @Builder.Default
        @Size(max = 50, message = "State is exceeded 50 characters")
        private Optional<String> state = Optional.empty();

        @Builder.Default
        @Size(max = 5, message = "ZipCode is exceeded 5 characters")
        private Optional<String> zipCode = Optional.empty();
    }


}
