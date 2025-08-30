package mini.integration.customerservice.infrastructure.querybus;

import mini.integration.lib.module.exception.GeneralException;
import mini.integration.lib.module.QueryHandler;

public interface QueryBus {
    <C, R> void registerHandler(Class<C> type, QueryHandler<C, R> handler);

    <C, R> R dispatch(C query) throws GeneralException;
}
