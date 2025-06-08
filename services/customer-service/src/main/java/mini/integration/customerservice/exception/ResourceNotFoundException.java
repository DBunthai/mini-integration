package mini.integration.customerservice.exception;

import mini.integration.customerservice.lib.GeneralException;

public class ResourceNotFoundException extends GeneralException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
