package mini.integration.customerservice.application.query.handler;


import mini.integration.customerservice.application.query.CustomerSettingNotificationQuery;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingNotificationDTO;
import mini.integration.customerservice.lib.QueryHandler;
import org.springframework.data.domain.Page;

public interface CustomerSettingNotificationQueryHandler extends QueryHandler<CustomerSettingNotificationQuery, Page<CustomerSettingNotificationDTO>> {
}
