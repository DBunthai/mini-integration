package mini.integration.customerservice.command;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.event.BalancePostedEvent;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.lib.topic.CustomerEvent;
import mini.integration.customerservice.presentation.command.CustomerController;
import mini.integration.customerservice.presentation.query.CustomerQueryController;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@SpringBootTest
public class PostedBalanceHandlerTest {

    @Autowired
    CommandBus commandBus;

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerQueryController customerQueryController;

    @Autowired
    CustomerCommandMapper mapper;

    @Autowired
    KafkaTemplate<String, BalancePostedEvent> kafkaTemplate;


    @Test
    void customerRegisterTest() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        BalancePostedEvent balancePostedEvent =
            BalancePostedEvent.builder()
                .customerId(registerDTO.getId())
                .postedAmount(BigDecimal.valueOf(25000, 4))
                .registeredDate(OffsetDateTime.now().minusYears(1))
                .build();
        System.out.println(balancePostedEvent);
        kafkaTemplate.send(CustomerEvent.DAILY_BALANCE_POSTED_EVENT, balancePostedEvent);
    }

}
