package mini.integration.customerservice.command;

import mini.integration.customerservice.application.command.CustomerNotificationChannelConfigCommand;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.handler.CustomerNotificationChannelConfigCommandHandler;
import mini.integration.customerservice.application.command.handler.CustomerRegisterCommandHandler;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.application.query.CustomerSettingNotificationQuery;
import mini.integration.customerservice.application.query.handler.impl.CustomerSettingNotificationQueryHandlerImpl;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingNotificationDTO;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class CustomerNotificationChannelHandlerTest {

    @Autowired
    CustomerNotificationChannelConfigCommandHandler customerNotificationChannelConfigCommandHandler;

    @Autowired
    CustomerSettingNotificationQueryHandlerImpl customerSettingNotificationQueryHandler;

    @Autowired
    CustomerCommandMapper mapper;

    @Autowired
    CustomerRegisterCommandHandler customerRegisterCommandHandler;

    @Test
    void testCreateAndUpdateCustomerNotificationChannelConfigHandle() throws GeneralException {


        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerRegisterCommandHandler.handle(registerCommand);


        var customerSettingNotification = customerSettingNotificationQueryHandler.handle(
            CustomerSettingNotificationQuery.builder()
                .customerId(registerDTO.getId().toString())
                .build()

        );

        CustomerSettingNotificationDTO.CustomerNotificationConfigDTO notificationConfig =
            customerSettingNotification.getContent().get(0).getNotificationConfigs().get(0);


        // Create new config
        var command = CustomerNotificationChannelConfigCommand.builder()
            .customerId(registerDTO.getId().toString())
            .customerNotificationConfigId(notificationConfig.getId().toString())
            .enabled(false)
            .build();
        var result = customerNotificationChannelConfigCommandHandler.handle(command);
        assertThat(result.isEnabled()).isFalse();


        // Update config
        command = CustomerNotificationChannelConfigCommand.builder()
            .customerId(registerDTO.getId().toString())
            .customerNotificationConfigId(notificationConfig.getId().toString())
            .enabled(true)
            .build();
        var result2 = customerNotificationChannelConfigCommandHandler.handle(command);
        assertThat(result2.isEnabled()).isTrue();

    }
}




