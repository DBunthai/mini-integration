package mini.integration.customerservice.lib;

import mini.integration.customerservice.exception.BusinessRuleException;

public interface CommandHandler<T> {
    void handle(T command) throws BusinessRuleException;
}
