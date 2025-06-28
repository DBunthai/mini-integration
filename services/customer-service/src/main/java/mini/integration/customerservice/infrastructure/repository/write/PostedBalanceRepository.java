package mini.integration.customerservice.infrastructure.repository.write;

import mini.integration.customerservice.domain.PostedBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostedBalanceRepository extends JpaRepository<PostedBalance, UUID> {
}
