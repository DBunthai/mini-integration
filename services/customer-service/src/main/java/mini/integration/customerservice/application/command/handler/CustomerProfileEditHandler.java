package mini.integration.customerservice.application.command.handler;

import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Address;
import mini.integration.customerservice.domain.Contact;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.enumtype.Gender;
import mini.integration.customerservice.domain.event.CustomerProfileEditedEvent;
import mini.integration.customerservice.exception.ResourceNotFoundException;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepo;
import mini.integration.customerservice.lib.CommandHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class CustomerProfileEditHandler implements CommandHandler<CustomerProfileEditCommand> {


    private final CustomerWriteRepo customerWriteRepo;
    private final CustomerCommandMapper customerCommandMapper;
    private final ApplicationEventPublisher eventPublisher;

    public CustomerProfileEditHandler(CustomerWriteRepo customerWriteRepo, CustomerCommandMapper customerCommandMapper, ApplicationEventPublisher eventPublisher) {
        this.customerWriteRepo = customerWriteRepo;
        this.customerCommandMapper = customerCommandMapper;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void handle(CustomerProfileEditCommand command) throws ResourceNotFoundException {

        UUID id = UUID.fromString(command.getId());

        final Customer customer = customerWriteRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer is not found"));


        command.getFirstName().ifPresent(customer::setFirstName);
        command.getLastName().ifPresent(customer::setLastName);
        command.getGender().ifPresent(gender -> customer.setGender(Gender.valueOf(gender)));
        command.getContactCommand().ifPresent(contactCommand -> {
            Contact contact = customer.getContact();
            Contact.ContactBuilder contactBuilder = Contact.builder()
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber());

            contactCommand.getEmail().ifPresent(contactBuilder::email);
            contactCommand.getPhoneNumber().ifPresent(contactBuilder::phoneNumber);
            customer.setContact(contactBuilder.build());
        });

        command.getAddressCommand().ifPresent(addressCommand -> {
            Address address = customer.getAddress();
            Address.AddressBuilder addressBuilder = Address.builder()
                .line(address.getLine())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode());

            addressCommand.getLine().ifPresent(addressBuilder::line);
            addressCommand.getCity().ifPresent(addressBuilder::city);
            addressCommand.getState().ifPresent(addressBuilder::state);
            addressCommand.getZipCode().ifPresent(addressBuilder::zipCode);

            customer.setAddress(addressBuilder.build());
        });

        Customer editedCustomer = customerWriteRepo.save(customer);
        log.info("Editing customer profile");
        customerWriteRepo.save(customer);
        log.info("Customer profile edited: {}", editedCustomer);
        CustomerProfileEditedEvent customerEditedEvent = customerCommandMapper.customerToCustomerProfileEditedEvent(customer);
        eventPublisher.publishEvent(customerEditedEvent);
        log.info("Published customer.edited: {}", customerEditedEvent);
    }
}
