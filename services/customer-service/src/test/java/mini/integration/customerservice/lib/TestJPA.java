package mini.integration.customerservice.lib;

import mini.integration.customerservice.domain.Address;
import mini.integration.customerservice.domain.Contact;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.enumtype.Gender;
import mini.integration.customerservice.infrastructure.repository.read.CustomerReadRepo;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepo;
import mini.integration.customerservice.lib.util.FakerLib;
import mini.integration.customerservice.util.UtilTest;
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



        Customer createdCustomer = customerWriteRepo.save(UtilTest.create());

        assertThatNoException().as("READ DB - Customer not found")
            .isThrownBy(() -> customerReadRepo.findById(createdCustomer.getId()));

    }

}
