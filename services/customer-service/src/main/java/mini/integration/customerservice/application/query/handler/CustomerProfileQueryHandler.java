package mini.integration.customerservice.application.query.handler;

import mini.integration.customerservice.application.query.CustomerProfileQuery;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.lib.module.QueryHandler;


public interface CustomerProfileQueryHandler extends QueryHandler<CustomerProfileQuery, CustomerProfileDTO> {

}
