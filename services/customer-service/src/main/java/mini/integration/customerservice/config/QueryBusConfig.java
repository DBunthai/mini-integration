package mini.integration.customerservice.config;

import jakarta.annotation.PostConstruct;

import mini.integration.customerservice.application.query.CustomerProfileQuery;
import mini.integration.customerservice.application.query.PostedBalanceQuery;
import mini.integration.customerservice.application.query.handler.CustomerProfileQueryHandler;
import mini.integration.customerservice.application.query.handler.PostedBalanceQueryHandler;
import mini.integration.customerservice.infrastructure.querybus.QueryBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryBusConfig {

    private final QueryBus queryBus;
    private final CustomerProfileQueryHandler customerProfileQueryHandler;
    private final PostedBalanceQueryHandler postedBalanceQueryHandler;

    public QueryBusConfig(QueryBus queryBus, CustomerProfileQueryHandler customerProfileQueryHandler, PostedBalanceQueryHandler postedBalanceQueryHandler) {
        this.queryBus = queryBus;
        this.customerProfileQueryHandler = customerProfileQueryHandler;
        this.postedBalanceQueryHandler = postedBalanceQueryHandler;
    }

    @PostConstruct
    public void registerHandlers() {
        queryBus.registerHandler(CustomerProfileQuery.class, customerProfileQueryHandler);
        queryBus.registerHandler(PostedBalanceQuery.class, postedBalanceQueryHandler);
    }
}
