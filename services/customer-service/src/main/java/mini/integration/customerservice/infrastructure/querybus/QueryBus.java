package mini.integration.customerservice.infrastructure.querybus;

import mini.integration.customerservice.lib.QueryHandler;
import mini.integration.customerservice.lib.exception.GeneralException;

public interface QueryBus {
     <C, R> void registerHandler(Class<C> type, QueryHandler<C, R> handler);
     <C, R> R dispatch(C query) throws GeneralException;
}
