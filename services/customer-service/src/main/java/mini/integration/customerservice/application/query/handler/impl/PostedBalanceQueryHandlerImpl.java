package mini.integration.customerservice.application.query.handler.impl;

import mini.integration.customerservice.application.query.PostedBalanceQuery;
import mini.integration.customerservice.application.query.handler.PostedBalanceQueryHandler;
import mini.integration.customerservice.application.query.mapper.CustomerQueryMapper;
import mini.integration.customerservice.domain.PostedBalance;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.customerservice.infrastructure.repository.read.PostedBalanceReadRepository;
import mini.integration.customerservice.lib.QueryHandler;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostedBalanceQueryHandlerImpl implements PostedBalanceQueryHandler {

    private final PostedBalanceReadRepository postedBalanceReadRepository;

    private final CustomerQueryMapper customerQueryMapper;

    public PostedBalanceQueryHandlerImpl(PostedBalanceReadRepository postedBalanceReadRepository, CustomerQueryMapper customerQueryMapper) {
        this.postedBalanceReadRepository = postedBalanceReadRepository;
        this.customerQueryMapper = customerQueryMapper;
    }

    @Override
    public Optional<PostedBalanceDTO> handle(PostedBalanceQuery query) throws GeneralException {


        Optional<PostedBalance> postedBalance = postedBalanceReadRepository.findByCustomer_Id(query.getCustomerId());

        return postedBalance.map(customerQueryMapper::postedBalanceToPostedBalancedDTO).or(Optional::empty);
    }
}
