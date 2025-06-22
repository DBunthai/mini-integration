package mini.integration.customerservice.infrastructure.commandbus;

import mini.integration.customerservice.exception.BusinessRuleException;
import mini.integration.customerservice.lib.CommandHandler;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandBusImpl implements CommandBus {
    private final Map<Class<?>, CommandHandler<?>> handlers = new HashMap<>();

    public <C> void registerHandler(Class<C> type, CommandHandler<C> handler) {
        handlers.put(type, handler);
    }

    public <C> void dispatch(C command) throws GeneralException {
        CommandHandler<C> handler = (CommandHandler<C>) handlers.get(command.getClass());
        if (handler != null) handler.handle(command);
        else throw new IllegalArgumentException("No handler found for " + command.getClass());
    }
}
