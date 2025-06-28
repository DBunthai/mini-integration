package mini.integration.customerservice.infrastructure.repository.read;

import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.lib.JpaReadOnlyRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerReadRepository extends JpaReadOnlyRepository<Customer, UUID> {
}
