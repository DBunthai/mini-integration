package mini.integration.customerservice.application.command.mapper;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.event.CustomerProfileEditedEvent;
import mini.integration.customerservice.domain.event.CustomerRegisteredEvent;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.lib.MapperResolver;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MapperResolver.class)
public interface CustomerCommandMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "contact", source = "contact")
    @Mapping(target = "description", source = "gender")
    Customer customerRegisterCommandToCustomer(CustomerRegisterCommand command);

    @InheritInverseConfiguration(name = "customerRegisterCommandToCustomer")
    CustomerRegisterCommand customerToRegisterCommand(Customer customer);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdDate", source = "createdDate")
    CustomerRegisterDTO customerToCustomerRegisterDTO(Customer customer);


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
