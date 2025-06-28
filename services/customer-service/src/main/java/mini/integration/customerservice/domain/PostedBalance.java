package mini.integration.customerservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mini.integration.customerservice.lib.EntityBased;
import org.springframework.data.annotation.Immutable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostedBalance extends EntityBased {

    @ManyToOne
    private Customer customer;
    private BigDecimal postedAmount;
    private OffsetDateTime registeredDate;


}
