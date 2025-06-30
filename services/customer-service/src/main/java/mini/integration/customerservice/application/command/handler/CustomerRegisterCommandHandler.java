package mini.integration.customerservice.application.command.handler;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.infrastructure.dto.CustomerRegisterDTO;
import mini.integration.customerservice.lib.CommandHandler;

public interface CustomerRegisterCommandHandler extends CommandHandler<CustomerRegisterCommand, CustomerRegisterDTO> {

}
