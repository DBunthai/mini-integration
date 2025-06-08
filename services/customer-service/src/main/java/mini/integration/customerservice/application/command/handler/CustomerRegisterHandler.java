package mini.integration.customerservice.application.command.handler;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.event.CustomerRegisterEvent;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepo;
import mini.integration.customerservice.lib.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomerRegisterHandler implements CommandHandler<CustomerRegisterCommand> {


    CustomerWriteRepo customerWriteRepo;
    CustomerCommandMapper customerCommandMapper;

    ApplicationEventPublisher eventPublisher;


    @Autowired
    public CustomerRegisterHandler(CustomerWriteRepo customerWriteRepo, CustomerCommandMapper customerCommandMapper, ApplicationEventPublisher eventPublisher) {
        this.customerWriteRepo = customerWriteRepo;
        this.customerCommandMapper = customerCommandMapper;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void handle(CustomerRegisterCommand command) {
//        customerWriteRepo.save(command);
        Customer customer = customerCommandMapper.customerRegisterCommandToCustomer(command);
        customer = customerWriteRepo.save(customer);
        CustomerRegisterEvent customerRegisterEvent = customerCommandMapper.customerToCustomerRegisteredEvent(customer);
        eventPublisher.publishEvent(customerRegisterEvent);
    }
}
