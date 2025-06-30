package mini.integration.customerservice.infrastructure.repository.read;

import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.PostedBalance;
import mini.integration.customerservice.lib.JpaReadOnlyRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostedBalanceReadRepository extends JpaReadOnlyRepository<PostedBalance, UUID> {

    Optional<PostedBalance> findByCustomer_Id(UUID customerId);

}
