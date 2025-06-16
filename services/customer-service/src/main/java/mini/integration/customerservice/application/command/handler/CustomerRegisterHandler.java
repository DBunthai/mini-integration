package mini.integration.customerservice.application.command.handler;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.event.CustomerRegisterEvent;
import mini.integration.customerservice.exception.BusinessRuleException;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepo;
import mini.integration.customerservice.lib.CommandHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegisterHandler implements CommandHandler<CustomerRegisterCommand> {


    private final CustomerWriteRepo customerWriteRepo;
    private final CustomerCommandMapper customerCommandMapper;

    private final ApplicationEventPublisher eventPublisher;

    public CustomerRegisterHandler(CustomerWriteRepo customerWriteRepo, CustomerCommandMapper customerCommandMapper, ApplicationEventPublisher eventPublisher) {
        this.customerWriteRepo = customerWriteRepo;
        this.customerCommandMapper = customerCommandMapper;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void handle(CustomerRegisterCommand command) throws BusinessRuleException {

        if (customerWriteRepo.existsByContactEmail(command.getContact().getEmail())) {
            throw new BusinessRuleException("Email has been used");
        }

        if (customerWriteRepo.existsByContactPhoneNumber(command.getContact().getEmail())) {
            throw new BusinessRuleException("Phone number has been used");
        }

        Customer customer = customerCommandMapper.customerRegisterCommandToCustomer(command);
        customer = customerWriteRepo.save(customer);
        CustomerRegisterEvent customerRegisterEvent = customerCommandMapper.customerToCustomerRegisteredEvent(customer);
        eventPublisher.publishEvent(customerRegisterEvent);
    }
}
