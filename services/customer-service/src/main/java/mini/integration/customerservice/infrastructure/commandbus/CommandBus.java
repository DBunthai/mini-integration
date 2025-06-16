package mini.integration.customerservice.infrastructure.commandbus;

import mini.integration.customerservice.exception.BusinessRuleException;
import mini.integration.customerservice.lib.CommandHandler;

public interface CommandBus {

    <C> void registerHandler(Class<C> type, CommandHandler<C> handler);

    <C> void dispatch(C command) throws BusinessRuleException;
}