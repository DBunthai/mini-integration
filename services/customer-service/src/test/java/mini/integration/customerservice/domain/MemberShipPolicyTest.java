package mini.integration.customerservice.domain;

import mini.integration.customerservice.domain.enumtype.MemberShipType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberShipPolicyTest {


    @Test
    void test() {
        MemberShipType memberShipType = MemberShipPolicy
            .getMemberShip(BigDecimal.valueOf(19999.9999), OffsetDateTime.now());

        assertThat(memberShipType)
            .as("MemberShipPolicy, SILVER policy")
            .isEqualTo(MemberShipType.SILVER);
    }

}
