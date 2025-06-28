package mini.integration.customerservice.application.command.handler;

import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.command.PostedBalanceCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.PostedBalance;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.customerservice.infrastructure.repository.write.PostedBalanceRepository;
import mini.integration.customerservice.lib.CommandHandler;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Log4j2
public class PostedBalanceCommandHandler implements CommandHandler<PostedBalanceCommand, PostedBalanceDTO> {

    private final PostedBalanceRepository postedBalanceRepo;
    private final CustomerCommandMapper mapper;

    public PostedBalanceCommandHandler(PostedBalanceRepository postedBalanceRepo, CustomerCommandMapper mapper) {
        this.postedBalanceRepo = postedBalanceRepo;
        this.mapper = mapper;
    }


    @Override
    public PostedBalanceDTO handle(PostedBalanceCommand command) throws GeneralException {

        PostedBalance postedBalance = mapper.postedBalanceCommandToPostedBalance(command);

        PostedBalance createdPostedBalance = postedBalanceRepo.save(postedBalance);

        return mapper.postedBalanceToPostedBalanceDTO(createdPostedBalance);
    }
}
