package mini.integration.customerservice.application.query.mapper;

import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.PostedBalance;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.customerservice.lib.MapperResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MapperResolver.class)
public interface CustomerQueryMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "contact", source = "contact")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "memberShip", source = "memberShip")
    CustomerProfileDTO customerToCustomerProfileDTO(Customer customer);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "postedAmount", source = "postedAmount")
    @Mapping(target = "registeredDate", source = "registeredDate")
    PostedBalanceDTO postedBalanceToPostedBalancedDTO(PostedBalance postedBalance);

}
