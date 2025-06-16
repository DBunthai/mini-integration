package mini.integration.customerservice.lib;

import mini.integration.customerservice.domain.Address;
import mini.integration.customerservice.domain.Contact;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.enumtype.Gender;
import mini.integration.customerservice.infrastructure.repository.read.CustomerReadRepo;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepo;
import mini.integration.customerservice.lib.util.FakerLib;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest
public class TestJPA {

    @Autowired
    CustomerWriteRepo customerWriteRepo;

    @Autowired
    CustomerReadRepo customerReadRepo;

    @Test
    public void TestJPA_Read_Write() {

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

        Customer createdCustomer = customerWriteRepo.save(customer);

        assertThatNoException().as("READ DB - Customer not found")
            .isThrownBy(() -> customerReadRepo.findById(createdCustomer.getId()));

    }

}
