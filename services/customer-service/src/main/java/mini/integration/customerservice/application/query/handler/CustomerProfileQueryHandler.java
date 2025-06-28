package mini.integration.customerservice.application.query.handler;

import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.query.CustomerProfileQuery;
import mini.integration.customerservice.application.query.mapper.CustomerQueryMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.exception.ResourceNotFoundException;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.customerservice.infrastructure.repository.read.CustomerReadRepository;
import mini.integration.customerservice.lib.QueryHandler;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class CustomerProfileQueryHandler implements QueryHandler<CustomerProfileQuery, CustomerProfileDTO> {


    private final CustomerReadRepository customerReadRepo;
    private final CustomerQueryMapper customerQueryMapper;

    public CustomerProfileQueryHandler(CustomerReadRepository customerReadRepo, CustomerQueryMapper customerQueryMapper) {
        this.customerReadRepo = customerReadRepo;
        this.customerQueryMapper = customerQueryMapper;
    }

    @Override
    public CustomerProfileDTO handle(CustomerProfileQuery query) throws GeneralException {

        UUID id = UUID.fromString(query.getId());

        final Customer customer = customerReadRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer is not found"));

        log.info("Customer: {} has found", id);

        return customerQueryMapper.customerToCustomerProfileDTO(customer);
    }
}
