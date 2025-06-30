package mini.integration.customerservice.application.command.handler;

import mini.integration.customerservice.application.command.PostedBalanceCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.PostedBalance;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.customerservice.infrastructure.repository.write.PostedBalanceRepository;
import mini.integration.customerservice.lib.CommandHandler;

public interface PostedBalanceCommandHandler extends CommandHandler<PostedBalanceCommand, PostedBalanceDTO> {

}
