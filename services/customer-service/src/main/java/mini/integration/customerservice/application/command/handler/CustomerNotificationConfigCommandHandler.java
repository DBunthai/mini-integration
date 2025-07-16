package mini.integration.customerservice.application.command.handler;

import mini.integration.customerservice.application.command.CustomerNotificationConfigCommand;
import mini.integration.customerservice.lib.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface CustomerNotificationConfigCommandHandler extends CommandHandler<CustomerNotificationConfigCommand, Optional<Void>> {
}
