package mini.integration.customerservice.command;

import mini.integration.customerservice.application.command.CustomerRegisterAddressCommand;
import mini.integration.customerservice.application.command.CustomerRegisterContactCommand;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import mini.integration.customerservice.lib.util.FakerLib;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class CustomerRegisteredCommandHandlerTest {

    @Autowired
    CommandBus commandBus;

    @Test
    void customerRegisterTest() {

        var faker = FakerLib.faker();
        var address = faker.address();
        CustomerRegisterCommand customerRegisterCommand =
            CustomerRegisterCommand.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .gender(faker.gender().binaryTypes().toUpperCase())
                .address(
                    CustomerRegisterAddressCommand.builder()
                        .line(String.join(address.buildingNumber(), address.streetName()))
                        .city(address.city())
                        .state(address.state())
                        .zipCode(address.zipCode())
                        .build()
                )
                .contact(
                    CustomerRegisterContactCommand.builder()
                        .email(faker.internet().emailAddress())
                        .phoneNumber(faker.phoneNumber().cellPhone())
                        .build()
                )
                .build();


        assertThatNoException()
            .as("Not expected error")
            .isThrownBy(() -> commandBus.dispatch(customerRegisterCommand));
    }

}
