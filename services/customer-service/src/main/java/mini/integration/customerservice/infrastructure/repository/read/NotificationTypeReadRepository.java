package mini.integration.customerservice.infrastructure.repository.read;

import mini.integration.customerservice.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationTypeReadRepository extends JpaRepository<NotificationType, UUID> {
}
