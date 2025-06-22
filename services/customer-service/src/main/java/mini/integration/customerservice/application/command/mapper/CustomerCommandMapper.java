package mini.integration.customerservice.application.command.mapper;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.event.CustomerProfileEditedEvent;
import mini.integration.customerservice.domain.event.CustomerRegisteredEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerCommandMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "contact", source = "contact")
    Customer customerRegisterCommandToCustomer(CustomerRegisterCommand command);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "contact.email")
    @Mapping(target = "phoneNumber", source = "contact.phoneNumber")
    CustomerRegisteredEvent customerToCustomerRegisteredEvent(Customer customer);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "contact.email")
    @Mapping(target = "phoneNumber", source = "contact.phoneNumber")
    CustomerProfileEditedEvent customerToCustomerProfileEditedEvent(Customer customer);

}
