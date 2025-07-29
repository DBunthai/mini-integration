package mini.integration.customerservice.infrastructure.commandbus;

import mini.integration.customerservice.exception.BusinessRuleException;
import mini.integration.customerservice.lib.CommandHandler;
import mini.integration.customerservice.lib.exception.GeneralException;

public interface CommandBus {

    <C, R> void registerHandler(Class<C> type, CommandHandler<C, R> handler);

    <C, R> R dispatch(C command) throws GeneralException;
}
