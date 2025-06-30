package mini.integration.customerservice.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@SpringBootTest
public class MemberShipPolicyTest {


    @Test
    void test() {
        MemberShipPolicy.getMemberShip(BigDecimal.valueOf(19999.9999), OffsetDateTime.now());
    }

}
