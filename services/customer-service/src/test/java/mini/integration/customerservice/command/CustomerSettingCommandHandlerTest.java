package mini.integration.customerservice.command;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.handler.CustomerRegisterCommandHandler;
import mini.integration.customerservice.application.command.handler.CustomerSettingAddCommandHandler;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.infrastructure.repository.read.CustomerSettingReadRepository;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CustomerSettingCommandHandlerTest {

    @Autowired
    CustomerSettingAddCommandHandler customerSettingAddCommandHandler;

    @Autowired
    CustomerSettingReadRepository customerSettingReadRepository;

    @Autowired
    CustomerCommandMapper mapper;

    @Autowired
    CustomerRegisterCommandHandler customerRegisterCommandHandler;


    @Test
    public void testCheckCustomerSettingAfterCustomerRegistered() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerRegisterCommandHandler.handle(registerCommand);

        boolean customerSettingFound =
            this.customerSettingReadRepository.findCustomerSettingByCustomer_Id(registerDTO.getId()).isPresent();

        assertThat(customerSettingFound)
            .as("Customer Setting is not found after register")
            .isEqualTo(true);

    }


}
