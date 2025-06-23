package mini.integration.customerservice.config;

import jakarta.annotation.PostConstruct;

import mini.integration.customerservice.application.query.CustomerProfileQuery;
import mini.integration.customerservice.application.query.handler.CustomerProfileQueryHandler;
import mini.integration.customerservice.infrastructure.querybus.QueryBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryBusConfig {

    private final QueryBus queryBus;
    private final CustomerProfileQueryHandler customerProfileQueryHandler;

    public QueryBusConfig(QueryBus queryBus, CustomerProfileQueryHandler customerProfileQueryHandler) {
        this.queryBus = queryBus;
        this.customerProfileQueryHandler = customerProfileQueryHandler;
    }

    @PostConstruct
    public void registerHandlers() {
        queryBus.registerHandler(CustomerProfileQuery.class, customerProfileQueryHandler);
    }
}
