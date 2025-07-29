package mini.integration.customerservice.infrastructure.commandbus;

import mini.integration.customerservice.lib.CommandHandler;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandBusImpl implements CommandBus {
    private final Map<Class<?>, CommandHandler<?, ?>> handlers = new HashMap<>();

    public <C, R> void registerHandler(Class<C> type, CommandHandler<C, R> handler) {
        handlers.put(type, handler);
    }

    public <C, R> R dispatch(C command) throws GeneralException {
        CommandHandler<C, R> handler = (CommandHandler<C, R>) handlers.get(command.getClass());
        if (handler != null) {
            return handler.handle(command);
        } else
            throw new GeneralException("No handler found for " + command.getClass());
    }
}
