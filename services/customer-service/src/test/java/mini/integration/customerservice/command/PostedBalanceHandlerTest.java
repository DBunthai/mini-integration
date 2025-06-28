package mini.integration.customerservice.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.application.command.CustomerRegisterAddressCommand;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.CustomerRegisterContactCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.event.BalancePostedEvent;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.lib.topic.CustomerEvent;
import mini.integration.customerservice.lib.util.FakerLib;
import mini.integration.customerservice.presentation.command.CustomerController;
import mini.integration.customerservice.presentation.query.CustomerQueryController;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatNoException;

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
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void customerRegisterTest() throws GeneralException, JsonProcessingException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        BalancePostedEvent balancePostedEvent =
            BalancePostedEvent.builder()
                .customerId(registerDTO.getId())
                .postedAmount("25000.00")
                .registeredDate(OffsetDateTime.now().minusYears(1).toString())
                .build();

        kafkaTemplate.send(CustomerEvent.DAILY_BALANCE_POSTED_EVENT, objectMapper.writeValueAsString(balancePostedEvent));
    }

}
