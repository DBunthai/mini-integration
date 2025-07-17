package mini.integration.customerservice.config;

import jakarta.annotation.PostConstruct;
import mini.integration.customerservice.application.command.CustomerNotificationConfigCommand;
import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.CustomerSettingAddCommand;
import mini.integration.customerservice.application.command.PostedBalanceCommand;
import mini.integration.customerservice.application.command.handler.CustomerNotificationConfigCommandHandler;
import mini.integration.customerservice.application.command.handler.CustomerProfileEditCommandHandler;
import mini.integration.customerservice.application.command.handler.CustomerRegisterCommandHandler;
import mini.integration.customerservice.application.command.handler.CustomerSettingAddCommandHandler;
import mini.integration.customerservice.application.command.handler.PostedBalanceCommandHandler;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandBusConfig {
    private final CommandBus commandBus;
    private final CustomerRegisterCommandHandler customerRegisterCommandHandler;
    private final CustomerProfileEditCommandHandler customerProfileEditCommandHandler;
    private final PostedBalanceCommandHandler postedBalanceCommandHandler;
    private final CustomerNotificationConfigCommandHandler customerNotificationConfigCommandHandler;
    private final CustomerSettingAddCommandHandler customerSettingAddCommandHandler;

    public CommandBusConfig(CommandBus commandBus, CustomerRegisterCommandHandler customerRegisterCommandHandler, CustomerProfileEditCommandHandler customerProfileEditCommandHandler, PostedBalanceCommandHandler postedBalanceCommandHandler, CustomerNotificationConfigCommandHandler customerNotificationConfigCommandHandler, CustomerSettingAddCommandHandler customerSettingAddCommandHandler) {
        this.commandBus = commandBus;
        this.customerRegisterCommandHandler = customerRegisterCommandHandler;
        this.customerProfileEditCommandHandler = customerProfileEditCommandHandler;
        this.postedBalanceCommandHandler = postedBalanceCommandHandler;
        this.customerNotificationConfigCommandHandler = customerNotificationConfigCommandHandler;
        this.customerSettingAddCommandHandler = customerSettingAddCommandHandler;
    }

    @PostConstruct
    public void registerHandlers() {
        commandBus.registerHandler(CustomerRegisterCommand.class, customerRegisterCommandHandler);
        commandBus.registerHandler(CustomerProfileEditCommand.class, customerProfileEditCommandHandler);
        commandBus.registerHandler(PostedBalanceCommand.class, postedBalanceCommandHandler);
        commandBus.registerHandler(CustomerNotificationConfigCommand.class, customerNotificationConfigCommandHandler);
        commandBus.registerHandler(CustomerSettingAddCommand.class, customerSettingAddCommandHandler);
    }
}
