package mini.integration.customerservice.exception;

import mini.integration.customerservice.lib.GeneralException;

public class InvalidArgumentException extends GeneralException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
