package mini.integration.customerservice.exception;

import mini.integration.customerservice.lib.exception.GeneralException;

public class BusinessRuleException extends GeneralException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
