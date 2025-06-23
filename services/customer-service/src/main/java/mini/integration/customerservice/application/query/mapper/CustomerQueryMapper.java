package mini.integration.customerservice.application.query.mapper;

import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
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
    CustomerProfileDTO customerToCustomerProfileDTO(Customer customer);

}
