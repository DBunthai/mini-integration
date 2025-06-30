package mini.integration.customerservice.application.command.handler.impl;

import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.application.command.handler.CustomerProfileEditCommandHandler;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Address;
import mini.integration.customerservice.domain.Contact;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.enumtype.Gender;
import mini.integration.customerservice.domain.event.CustomerProfileEditedEvent;
import mini.integration.customerservice.exception.ResourceNotFoundException;
import mini.integration.customerservice.infrastructure.repository.write.CustomerWriteRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;


@Component
@Log4j2
public class CustomerProfileEditCommandHandlerImpl implements CustomerProfileEditCommandHandler {


    private final CustomerWriteRepository customerWriteRepo;
    private final CustomerCommandMapper customerCommandMapper;
    private final ApplicationEventPublisher eventPublisher;

    public CustomerProfileEditCommandHandlerImpl(CustomerWriteRepository customerWriteRepo, CustomerCommandMapper customerCommandMapper, ApplicationEventPublisher eventPublisher) {
        this.customerWriteRepo = customerWriteRepo;
        this.customerCommandMapper = customerCommandMapper;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public Optional<Void> handle(CustomerProfileEditCommand command) throws ResourceNotFoundException {

        UUID id = UUID.fromString(command.getId());

        final Customer customer = customerWriteRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer is not found"));

        if (nonNull(command.getFirstName())) {
            command.getFirstName().ifPresent(customer::setFirstName);
        }

        if (nonNull(command.getLastName())) {
            command.getLastName().ifPresent(customer::setLastName);
        }

        if (nonNull(command.getGender())) {
            command.getGender().ifPresent(gender -> customer.setGender(Gender.valueOf(gender)));
        }

        if (nonNull(command.getDescription())) {
            customer.setDescription(command.getDescription().orElse(null));
        }

        if (nonNull(command.getContact())) {
            command.getContact().ifPresent(contactCommand -> {
                Contact contact = customer.getContact();
                Contact.ContactBuilder contactBuilder = Contact.builder()
                    .email(contact.getEmail())
                    .phoneNumber(contact.getPhoneNumber());

                if (nonNull(contactCommand.getEmail())) {
                    contactCommand.getEmail().ifPresent(contactBuilder::email);
                }
                if (nonNull(contactCommand.getPhoneNumber())) {
                    contactCommand.getPhoneNumber().ifPresent(contactBuilder::phoneNumber);
                }
                customer.setContact(contactBuilder.build());
            });
        }


        if (nonNull(command.getAddress())) {

            command.getAddress().ifPresent(addressCommand -> {
                Address address = customer.getAddress();
                Address.AddressBuilder addressBuilder = Address.builder()
                    .line(address.getLine())
                    .city(address.getCity())
                    .state(address.getState())
                    .zipCode(address.getZipCode());

                if (nonNull(addressCommand.getLine())) {
                    addressCommand.getLine().ifPresent(addressBuilder::line);
                }
                if (nonNull(addressCommand.getCity())) {
                    addressCommand.getCity().ifPresent(addressBuilder::city);
                }
                if (nonNull(addressCommand.getState())) {
                    addressCommand.getState().ifPresent(addressBuilder::state);
                }
                if (nonNull(addressCommand.getZipCode())) {
                    addressCommand.getZipCode().ifPresent(addressBuilder::zipCode);
                }

                customer.setAddress(addressBuilder.build());
            });
        }


        Customer editedCustomer = customerWriteRepo.save(customer);
        log.info("Editing customer profile");
        customerWriteRepo.save(customer);
        log.info("Customer profile edited: {}", editedCustomer);
        CustomerProfileEditedEvent customerEditedEvent = customerCommandMapper.customerToCustomerProfileEditedEvent(customer);
        eventPublisher.publishEvent(customerEditedEvent);
        log.info("Published customer.edited: {}", customerEditedEvent);

        return Optional.empty();
    }
}
