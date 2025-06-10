package mini.integration.customerservice.config;

import jakarta.annotation.PostConstruct;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.handler.CustomerRegisterHandler;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandBusConfig {
    private final CommandBus commandBus;
    private final CustomerRegisterHandler customerRegisterHandler;

    public CommandBusConfig(CommandBus commandBus, CustomerRegisterHandler customerRegisterHandler) {
        this.commandBus = commandBus;
        this.customerRegisterHandler = customerRegisterHandler;
    }

    @PostConstruct
    public void registerHandlers() {
        commandBus.registerHandler(CustomerRegisterCommand.class, customerRegisterHandler);
    }
}
