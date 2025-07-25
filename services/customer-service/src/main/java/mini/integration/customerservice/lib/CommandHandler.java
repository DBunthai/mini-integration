package mini.integration.customerservice.lib;

import mini.integration.customerservice.lib.exception.GeneralException;

public interface CommandHandler<T, R> {
    R handle(T command) throws GeneralException;
}
