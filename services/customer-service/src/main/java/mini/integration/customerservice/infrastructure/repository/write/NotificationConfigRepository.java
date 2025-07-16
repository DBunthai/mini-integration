package mini.integration.customerservice.infrastructure.repository.write;

import mini.integration.customerservice.domain.NotificationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationConfigRepository extends JpaRepository<NotificationConfig, UUID> {

}
