package mini.integration.customerservice.infrastructure.repository.write;

import mini.integration.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerWriteRepository extends JpaRepository<Customer, UUID> {

    boolean existsByContactEmail(String email);
    boolean existsByContactPhoneNumber(String phoneNumber);

}
