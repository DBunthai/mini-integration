package mini.integration.customerservice.presentation;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.enumtype.MemberShipType;
import mini.integration.customerservice.domain.event.BalancePostedEvent;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.infrastructure.listener.kafka.BalancePostedListener;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.presentation.command.CustomerController;
import mini.integration.customerservice.presentation.query.CustomerQueryController;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerControllerMemberShipTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerQueryController customerQueryController;


    @Autowired
    CustomerCommandMapper mapper;

    @Autowired
    BalancePostedListener balancePostedListener;

    @Test
    void TestBronzeProfileWithEmptyPostedBalance() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        CustomerProfileDTO customerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();

        assertThat(customerProfileDTO.getMemberShip())
            .as("BRONZE with empty balance")
            .isEqualTo(MemberShipType.BRONZE);

    }

    @Test
    void TestBronzeProfileWithPostedBalance() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        CustomerProfileDTO customerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();

        balancePostedListener.dailyBalancePostedListening(BalancePostedEvent.builder()
            .customerId(customerProfileDTO.getId())
            .registeredDate(customerProfileDTO.getCreatedDate())
            .postedAmount(BigDecimal.valueOf(9999.9999).setScale(4, RoundingMode.UNNECESSARY))
            .build()
        );

        CustomerProfileDTO newCustomerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();
        assertThat(newCustomerProfileDTO.getMemberShip())
            .as("BRONZE with posted balance")
            .isEqualTo(MemberShipType.BRONZE);


    }

    @Test
    void TestSilverProfileWithPostedBalance() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        CustomerProfileDTO customerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();

        balancePostedListener.dailyBalancePostedListening(BalancePostedEvent.builder()
            .customerId(customerProfileDTO.getId())
            .registeredDate(customerProfileDTO.getCreatedDate())
            .postedAmount(BigDecimal.valueOf(19999.9999).setScale(4, RoundingMode.UNNECESSARY))
            .build()
        );

        CustomerProfileDTO newCustomerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();
        assertThat(newCustomerProfileDTO.getMemberShip())
            .as("Silver with posted balance")
            .isEqualTo(MemberShipType.SILVER);


    }


    @Test
    void TestGoldProfileWithPostedBalance() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        CustomerProfileDTO customerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();

        balancePostedListener.dailyBalancePostedListening(BalancePostedEvent.builder()
            .customerId(customerProfileDTO.getId())
            .registeredDate(customerProfileDTO.getCreatedDate())
            .postedAmount(BigDecimal.valueOf(49999.9999).setScale(4, RoundingMode.UNNECESSARY))
            .build()
        );

        CustomerProfileDTO newCustomerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();
        assertThat(newCustomerProfileDTO.getMemberShip())
            .as("Gold with posted balance")
            .isEqualTo(MemberShipType.GOLD);


    }


    @Test
    void TestDiamondProfileWithPostedBalance() throws GeneralException {

        Customer customerData = UtilTest.create();
        CustomerRegisterCommand registerCommand = mapper.customerToRegisterCommand(customerData);
        CustomerRegisterDTO registerDTO = customerController.register(registerCommand).getBody();

        CustomerProfileDTO customerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();

        balancePostedListener.dailyBalancePostedListening(BalancePostedEvent.builder()
            .customerId(customerProfileDTO.getId())
            .registeredDate(customerProfileDTO.getCreatedDate())
            .postedAmount(BigDecimal.valueOf(50000.0000).setScale(4, RoundingMode.UNNECESSARY))
            .build()
        );

        CustomerProfileDTO newCustomerProfileDTO = customerQueryController.getProfile(registerDTO.getId().toString()).getBody();
        assertThat(newCustomerProfileDTO.getMemberShip())
            .as("Diamond with posted balance")
            .isEqualTo(MemberShipType.DIAMOND);


    }
}
