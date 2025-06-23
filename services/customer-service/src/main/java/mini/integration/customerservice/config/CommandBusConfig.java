package mini.integration.customerservice.config;

import jakarta.annotation.PostConstruct;
import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.handler.CustomerProfileEditCommandHandler;
import mini.integration.customerservice.application.command.handler.CustomerRegisterCommandHandler;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandBusConfig {
    private final CommandBus commandBus;
    private final CustomerRegisterCommandHandler customerRegisterCommandHandler;
    private final CustomerProfileEditCommandHandler customerProfileEditCommandHandler;

    public CommandBusConfig(CommandBus commandBus, CustomerRegisterCommandHandler customerRegisterCommandHandler, CustomerProfileEditCommandHandler customerProfileEditCommandHandler) {
        this.commandBus = commandBus;
        this.customerRegisterCommandHandler = customerRegisterCommandHandler;
        this.customerProfileEditCommandHandler = customerProfileEditCommandHandler;
    }

    @PostConstruct
    public void registerHandlers() {
        commandBus.registerHandler(CustomerRegisterCommand.class, customerRegisterCommandHandler);
        commandBus.registerHandler(CustomerProfileEditCommand.class, customerProfileEditCommandHandler);
    }
}
