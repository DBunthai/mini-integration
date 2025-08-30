package mini.integration.customerservice.infrastructure.commandbus;

import mini.integration.customerservice.exception.BusinessRuleException;
import mini.integration.lib.module.CommandHandler;
import mini.integration.lib.module.exception.GeneralException;

public interface CommandBus {

    <C, R> void registerHandler(Class<C> type, CommandHandler<C, R> handler);

    <C, R> R dispatch(C command) throws GeneralException;
}
