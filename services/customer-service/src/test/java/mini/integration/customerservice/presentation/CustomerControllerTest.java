package mini.integration.customerservice.presentation;

import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepo;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.presentation.command.CustomerController;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerWriteRepo customerWriteRepo;

    @Test
    void TestEditProfile() throws GeneralException {
        Customer customer = customerWriteRepo.save(UtilTest.create());
        System.out.println("aaaaaaaaa: " + customer.getId());
        var editProfileCommand = CustomerProfileEditCommand.builder()
            .id(customer.getId().toString())
            .firstName(Optional.of("Sasa")).build();
        customerController.edit(editProfileCommand);


    }

}
