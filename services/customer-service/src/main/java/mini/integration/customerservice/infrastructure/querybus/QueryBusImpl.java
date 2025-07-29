package mini.integration.customerservice.infrastructure.querybus;

import mini.integration.customerservice.lib.CommandHandler;
import mini.integration.customerservice.lib.QueryHandler;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class QueryBusImpl implements QueryBus {

    private final Map<Class<?>, QueryHandler<?, ?>> handlers = new HashMap<>();

    @Override
    public <C, R> void registerHandler(Class<C> type, QueryHandler<C, R> handler) {
        handlers.put(type, handler);
    }

    @Override
    public <C, R> R dispatch(C query) throws GeneralException {
        QueryHandler<C, R> queryHandler = (QueryHandler<C, R>) handlers.get(query.getClass());
        if (Objects.nonNull(queryHandler)) {
            return queryHandler.handle(query);
        } else
            throw new GeneralException("No handler found for " + queryHandler.getClass());
    }
}
