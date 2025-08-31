package mini.integration.customerservice.infrastructure.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mini.integration.customerservice.domain.enumtype.Gender;
import mini.integration.customerservice.domain.enumtype.MemberShipType;
import mini.integration.lib.module.DTOBased;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CustomerProfileDTO implements DTOBased {

    private UUID id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String description;

    private ContactDTO contact;
    private AddressDTO address;

    private OffsetDateTime createdDate;

    private MemberShipType memberShip;


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ContactDTO implements DTOBased {

        private String phoneNumber;
        private String email;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AddressDTO implements DTOBased {

        private String line;
        private String city;
        private String state;
        private String zipCode;
    }


}
