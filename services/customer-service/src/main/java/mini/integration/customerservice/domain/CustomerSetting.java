package mini.integration.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
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
    private Customer customer;

    @OneToMany(mappedBy = "customerSetting")
    @JsonManagedReference
    private Set<CustomerNotificationConfig> customerNotificationConfigs;

    @Enumerated(EnumType.STRING)
    private RegularPostedBalance regularPostedBalance;

}
