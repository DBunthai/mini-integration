package mini.integration.customerservice.config;

import jakarta.annotation.PostConstruct;

import mini.integration.customerservice.application.query.CustomerProfileQuery;
import mini.integration.customerservice.application.query.CustomerSettingNotificationQuery;
import mini.integration.customerservice.application.query.CustomerSettingQuery;
import mini.integration.customerservice.application.query.PostedBalanceQuery;
import mini.integration.customerservice.application.query.handler.CustomerProfileQueryHandler;
import mini.integration.customerservice.application.query.handler.CustomerSettingNotificationQueryHandler;
import mini.integration.customerservice.application.query.handler.CustomerSettingQueryHandler;
import mini.integration.customerservice.application.query.handler.PostedBalanceQueryHandler;
import mini.integration.customerservice.infrastructure.querybus.QueryBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryBusConfig {

    private final QueryBus queryBus;
    private final CustomerProfileQueryHandler customerProfileQueryHandler;
    private final PostedBalanceQueryHandler postedBalanceQueryHandler;
    private final CustomerSettingNotificationQueryHandler customerSettingNotificationQueryHandler;
    private final CustomerSettingQueryHandler customerSettingQueryHandler;

    public QueryBusConfig(QueryBus queryBus, CustomerProfileQueryHandler customerProfileQueryHandler,
                    PostedBalanceQueryHandler postedBalanceQueryHandler,
                    CustomerSettingNotificationQueryHandler customerSettingNotificationQueryHandler,
                    CustomerSettingQueryHandler customerSettingQueryHandler) {
        this.queryBus = queryBus;
        this.customerProfileQueryHandler = customerProfileQueryHandler;
        this.postedBalanceQueryHandler = postedBalanceQueryHandler;
        this.customerSettingNotificationQueryHandler = customerSettingNotificationQueryHandler;
        this.customerSettingQueryHandler = customerSettingQueryHandler;
    }

    @PostConstruct
    public void registerHandlers() {
        queryBus.registerHandler(CustomerProfileQuery.class, customerProfileQueryHandler);
        queryBus.registerHandler(PostedBalanceQuery.class, postedBalanceQueryHandler);
        queryBus.registerHandler(CustomerSettingNotificationQuery.class, customerSettingNotificationQueryHandler);
        queryBus.registerHandler(CustomerSettingQuery.class, customerSettingQueryHandler);
    }
}
