package mini.integration.customerservice.lib;

import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.infrastructure.repository.read.CustomerReadRepository;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepository;
import mini.integration.customerservice.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest
public class TestJPA {

    @Autowired
    CustomerWriteRepository customerWriteRepo;

    @Autowired
    CustomerReadRepository customerReadRepo;

    @Test
    public void TestJPA_Read_Write() {



        Customer createdCustomer = customerWriteRepo.save(UtilTest.create());

        assertThatNoException().as("READ DB - Customer not found")
            .isThrownBy(() -> customerReadRepo.findById(createdCustomer.getId()));

    }

}
