package mini.integration.customerservice.exception;

import mini.integration.lib.module.exception.GeneralException;

public class InvalidArgumentException extends GeneralException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
