package mini.integration.customerservice.exception;

import mini.integration.customerservice.lib.GeneralException;

public class BusinessRuleException extends GeneralException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
