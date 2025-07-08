package mini.integration.customerservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mini.integration.customerservice.domain.enumtype.RegularPostedBalance;
import mini.integration.customerservice.lib.EntityBased;

import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerSetting extends EntityBased {

    @OneToOne
    Customer customer;

    @OneToMany
    Set<CustomerNotificationConfig> customerNotificationConfigs;

    RegularPostedBalance regularPostedBalance;

}
