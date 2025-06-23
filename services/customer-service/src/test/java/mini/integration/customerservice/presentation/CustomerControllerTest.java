package mini.integration.customerservice.presentation;

import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.presentation.command.CustomerController;
import mini.integration.customerservice.presentation.query.CustomerQueryController;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerQueryController customerQueryController;

    @Autowired
    CustomerCommandMapper mapper;

    @Test
    void TestEditProfile() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        var editProfileCommand = CustomerProfileEditCommand.builder()
            .id(registerDTO.getId())
            .firstName(Optional.of("Sasa")).build();
        customerController.edit(editProfileCommand);

        CustomerProfileDTO customerProfileDTO = customerQueryController.getProfile(registerDTO.getId()).getBody();

        assertThat(customerProfileDTO.getLastName())
            .as("Last name should match updated data")
            .isEqualTo(customerData.getLastName());

        assertThat(customerProfileDTO.getFirstName())
            .as("First name should be updated to 'Sasa'")
            .isEqualTo("Sasa");

    }

}
