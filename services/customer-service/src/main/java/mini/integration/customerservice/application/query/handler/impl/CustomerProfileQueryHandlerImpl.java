package mini.integration.customerservice.application.query.handler.impl;

import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.query.CustomerProfileQuery;
import mini.integration.customerservice.application.query.PostedBalanceQuery;
import mini.integration.customerservice.application.query.handler.CustomerProfileQueryHandler;
import mini.integration.customerservice.application.query.mapper.CustomerQueryMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.MemberShipPolicy;
import mini.integration.customerservice.domain.enumtype.MemberShipType;
import mini.integration.customerservice.exception.ResourceNotFoundException;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.customerservice.infrastructure.querybus.QueryBus;
import mini.integration.customerservice.infrastructure.repository.read.CustomerReadRepository;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Log4j2
public class CustomerProfileQueryHandlerImpl implements CustomerProfileQueryHandler {


    private final CustomerReadRepository customerReadRepo;
    private final CustomerQueryMapper customerQueryMapper;

    private final QueryBus queryBus;

    public CustomerProfileQueryHandlerImpl(CustomerReadRepository customerReadRepo, CustomerQueryMapper customerQueryMapper, QueryBus queryBus) {
        this.customerReadRepo = customerReadRepo;
        this.customerQueryMapper = customerQueryMapper;
        this.queryBus = queryBus;
    }

    private Customer findCustomerById(String customerId) throws ResourceNotFoundException {
        UUID id = UUID.fromString(customerId);

        final Customer customer = customerReadRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer is not found"));

        log.info("Customer: {} has found", id);

        return customer;

    }

    @Override
    public CustomerProfileDTO handle(CustomerProfileQuery query) throws GeneralException {

        Customer customer = findCustomerById(query.getId());
        var postedBalanceDTO = (Optional<PostedBalanceDTO>) queryBus.dispatch(
            PostedBalanceQuery.builder().customerId(customer.getId().toString()).build()
        );

        MemberShipType memberShip = postedBalanceDTO.map(p ->
            MemberShipPolicy.getMemberShip(p.getPostedAmount(), p.getRegisteredDate())
        ).orElse(MemberShipType.BRONZE);

        customer.setMemberShip(memberShip);
        return customerQueryMapper.customerToCustomerProfileDTO(customer);
    }
}
