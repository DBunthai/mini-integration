package mini.integration.customerservice.infrastructure.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerProfileDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String gender;
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
