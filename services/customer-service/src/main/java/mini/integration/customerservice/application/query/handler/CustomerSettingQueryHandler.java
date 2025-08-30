package mini.integration.customerservice.application.query.handler;


import mini.integration.customerservice.application.query.CustomerSettingQuery;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingDTO;
import mini.integration.lib.module.QueryHandler;

public interface CustomerSettingQueryHandler extends QueryHandler<CustomerSettingQuery, CustomerSettingDTO> {
}
