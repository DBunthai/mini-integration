package mini.integration.customerservice.application.command.handler.impl;

import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.command.PostedBalanceCommand;
import mini.integration.customerservice.application.command.handler.PostedBalanceCommandHandler;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.PostedBalance;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.customerservice.infrastructure.repository.write.PostedBalanceRepository;
import mini.integration.customerservice.lib.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PostedBalanceCommandHandlerImpl implements PostedBalanceCommandHandler {

    private final PostedBalanceRepository postedBalanceRepo;
    private final CustomerCommandMapper mapper;

    public PostedBalanceCommandHandlerImpl(PostedBalanceRepository postedBalanceRepo, CustomerCommandMapper mapper) {
        this.postedBalanceRepo = postedBalanceRepo;
        this.mapper = mapper;
    }


    @Override
    public PostedBalanceDTO handle(PostedBalanceCommand command) {

        log.info("PostedBalanceCommandHandler.handle; PostedBalanceCommand {}", command);

        PostedBalance postedBalance = mapper.postedBalanceCommandToPostedBalance(command);

        PostedBalance createdPostedBalance = postedBalanceRepo.save(postedBalance);
        PostedBalanceDTO postedBalanceDTO = mapper.postedBalanceToPostedBalanceDTO(createdPostedBalance);
        log.info("PostedBalanceCommandHandler.handle; inserted; postedBalanceDTO {}", postedBalanceDTO);

        return postedBalanceDTO;
    }
}
