package mini.integration.customerservice.exception;

import mini.integration.lib.module.exception.GeneralException;

public class BusinessRuleException extends GeneralException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
