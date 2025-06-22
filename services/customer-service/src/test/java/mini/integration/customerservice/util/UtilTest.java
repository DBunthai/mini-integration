package mini.integration.customerservice.util;

import mini.integration.customerservice.domain.Address;
import mini.integration.customerservice.domain.Contact;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.enumtype.Gender;
import mini.integration.customerservice.lib.util.FakerLib;

public class UtilTest {

    public static Customer create() {
        var faker = FakerLib.faker();
        var addrFaker = faker.address();
        var name = faker.name();

        Address address = Address.builder()
            .line(String.join(addrFaker.buildingNumber(), addrFaker.streetName()))
            .city(addrFaker.city())
            .state(addrFaker.state())
            .zipCode(addrFaker.zipCode())
            .build();

        Contact contact = Contact.builder()
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.phoneNumber().cellPhone())
            .build();

        Customer customer = Customer.builder()
            .firstName(name.firstName())
            .lastName(name.lastName())
            .gender(Gender.valueOf(faker.gender().binaryTypes().toUpperCase()))
            .address(address)
            .contact(contact)
            .build();

        return customer;
    }

}
