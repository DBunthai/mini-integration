package mini.integration.customerservice.lib;

import mini.integration.customerservice.exception.BusinessRuleException;
import mini.integration.customerservice.exception.ResourceNotFoundException;

public interface CommandHandler<T> {
    void handle(T command) throws BusinessRuleException, ResourceNotFoundException;
}
