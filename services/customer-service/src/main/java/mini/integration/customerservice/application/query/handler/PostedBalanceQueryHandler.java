package mini.integration.customerservice.application.query.handler;

import mini.integration.customerservice.application.query.PostedBalanceQuery;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.lib.module.QueryHandler;

import java.util.Optional;

public interface PostedBalanceQueryHandler extends QueryHandler<PostedBalanceQuery, Optional<PostedBalanceDTO>> {

}
