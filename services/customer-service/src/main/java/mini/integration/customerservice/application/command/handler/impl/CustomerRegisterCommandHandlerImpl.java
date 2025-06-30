package mini.integration.customerservice.application.command.handler.impl;

import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.handler.CustomerRegisterCommandHandler;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.event.CustomerRegisteredEvent;
import mini.integration.customerservice.exception.BusinessRuleException;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepository;
import mini.integration.customerservice.lib.CommandHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CustomerRegisterCommandHandlerImpl implements CustomerRegisterCommandHandler {


    private final CustomerWriteRepository customerWriteRepo;
    private final CustomerCommandMapper customerCommandMapper;
    private final ApplicationEventPublisher eventPublisher;

    public CustomerRegisterCommandHandlerImpl(CustomerWriteRepository customerWriteRepo, CustomerCommandMapper customerCommandMapper, ApplicationEventPublisher eventPublisher) {
        this.customerWriteRepo = customerWriteRepo;
        this.customerCommandMapper = customerCommandMapper;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public CustomerRegisterDTO handle(CustomerRegisterCommand command) throws BusinessRuleException {

        if (customerWriteRepo.existsByContactEmail(command.getContact().getEmail())) {
            throw new BusinessRuleException("Email has been used");
        }

        if (customerWriteRepo.existsByContactPhoneNumber(command.getContact().getEmail())) {
            throw new BusinessRuleException("Phone number has been used");
        }

        Customer customer = customerCommandMapper.customerRegisterCommandToCustomer(command);
        log.info("Registering customer");
        Customer registeredCustomer = customerWriteRepo.save(customer);
        log.info("Customer registered: {}", registeredCustomer);
        CustomerRegisteredEvent customerRegisteredEvent = customerCommandMapper.customerToCustomerRegisteredEvent(customer);
        eventPublisher.publishEvent(customerRegisteredEvent);
        log.info("Published customer.registered: {}", customerRegisteredEvent);

        return customerCommandMapper.customerToCustomerRegisterDTO(registeredCustomer);
    }
}
