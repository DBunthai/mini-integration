package mini.integration.customerservice.infrastructure.repository.write;

import mini.integration.customerservice.domain.CustomerSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerSettingRepository extends JpaRepository<CustomerSetting, UUID> {

    Optional<CustomerSetting> findCustomerSettingByCustomer_Id(UUID customerId);

}
