package mini.integration.customerservice.exception;

import mini.integration.lib.module.exception.GeneralException;

public class ResourceNotFoundException extends GeneralException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
