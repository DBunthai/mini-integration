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


    private Optional<@Size(max = 100, message = "First Name length must be 1 to 100") String> firstName;

    private Optional<@Size(max = 100, message = "Last Name length must be 1 to 100") String> lastName;

    private Optional<String> gender;

    private Optional<@Size(max = 500, message = "Description is exceed 500 characters") String> description;

    private Optional<CustomerEditContactCommand> contact;

    private Optional<CustomerEditAddressCommand> address;


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CustomerEditContactCommand {

        private Optional<@Size(max = 50, message = "Phone number is exceeded 50 characters") String> phoneNumber;

        private Optional<@Size(max = 320, message = "Email is exceeded 50 characters") @Email(message = "Invalid Email") String> email;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CustomerEditAddressCommand {

        private Optional<@Size(max = 100, message = "Line is exceeded 100 characters") String> line;

        private Optional<@Size(max = 50, message = "City is exceeded 50 characters") String> city;

        private Optional<@Size(max = 50, message = "State is exceeded 50 characters") String> state;

        private Optional<@Size(max = 5, message = "ZipCode is exceeded 5 characters") String> zipCode;
    }


}
