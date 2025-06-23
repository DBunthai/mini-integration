package mini.integration.customerservice.lib;

import mini.integration.customerservice.lib.exception.GeneralException;

public interface QueryHandler<T, R> {
    R handle(T query) throws GeneralException;
}
