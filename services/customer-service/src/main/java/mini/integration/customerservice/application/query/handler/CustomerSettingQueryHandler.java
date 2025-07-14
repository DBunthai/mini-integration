package mini.integration.customerservice.application.query.handler;


import mini.integration.customerservice.application.query.CustomerSettingQuery;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingDTO;
import mini.integration.customerservice.lib.QueryHandler;
import org.springframework.data.domain.Page;

public interface CustomerSettingQueryHandler extends QueryHandler<CustomerSettingQuery, CustomerSettingDTO> {
}
