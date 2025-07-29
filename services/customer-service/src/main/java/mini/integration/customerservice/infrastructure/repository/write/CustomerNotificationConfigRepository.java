package mini.integration.customerservice.infrastructure.repository.write;

import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.CustomerNotificationConfig;
import mini.integration.customerservice.domain.NotificationConfig;
import mini.integration.customerservice.domain.enumtype.NotificationChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerNotificationConfigRepository extends JpaRepository<CustomerNotificationConfig, UUID> {

    @Query("""
                        SELECT CNC FROM CustomerNotificationConfig CNC
                            WHERE
                                CNC.customerSetting.customer.id =  :customerId AND
                                CNC.notificationConfig.id = :notificationConfigId AND
                                CNC.notificationConfig.notificationChannel = :notificationChannel
                    """)
    Optional<CustomerNotificationConfig> findCustomerNotificationConfig(UUID customerId, UUID notificationConfigId,
                    NotificationChannel notificationChannel);

}
