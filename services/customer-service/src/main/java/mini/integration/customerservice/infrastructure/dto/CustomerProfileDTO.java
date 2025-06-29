package mini.integration.customerservice.infrastructure.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mini.integration.customerservice.domain.enumtype.Gender;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CustomerProfileDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String description;

    private ContactDTO contact;
    private AddressDTO address;

    private String createdDate;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ContactDTO {

        private String phoneNumber;
        private String email;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AddressDTO {

        private String line;
        private String city;
        private String state;
        private String zipCode;
    }


}
