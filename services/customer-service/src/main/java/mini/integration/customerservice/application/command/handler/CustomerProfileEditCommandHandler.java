package mini.integration.customerservice.application.command.handler;

import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.lib.module.CommandHandler;

import java.util.Optional;


public interface CustomerProfileEditCommandHandler extends CommandHandler<CustomerProfileEditCommand, Optional<Void>> {

}
