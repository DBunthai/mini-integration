package mini.integration.customerservice.presentation;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingNotificationDTO;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.presentation.command.CustomerController;
import mini.integration.customerservice.presentation.query.CustomerSettingQueryController;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerSettingQueryControllerTest {

    @Autowired
    CustomerSettingQueryController customerSettingQueryController;


    @Autowired
    CustomerController customerController;


    @Autowired
    CustomerCommandMapper mapper;

    @Test
    void getCustomerSettingQuery() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        var response = customerSettingQueryController.getCustomerSettingNotification(registerDTO.getId().toString());

        assertThat(response.getBody().getData()).as("Customer Setting is not null").isNotNull();

        var config = (CustomerSettingNotificationDTO) response.getBody().getData().get(0);

        assertThat(
            config.getNotificationConfigs()
        ).as("Customer Notification Config is not null").isNotNull();

    }

}
