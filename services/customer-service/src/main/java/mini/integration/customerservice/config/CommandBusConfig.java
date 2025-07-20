package mini.integration.customerservice.config;

import jakarta.annotation.PostConstruct;
import mini.integration.customerservice.application.command.CustomerNotificationChannelConfigCommand;
import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.application.command.CustomerSettingAddCommand;
import mini.integration.customerservice.application.command.PostedBalanceCommand;
import mini.integration.customerservice.application.command.handler.CustomerNotificationChannelConfigCommandHandler;
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
    private final CustomerNotificationChannelConfigCommandHandler customerNotificationConfigCommandHandler;
    private final CustomerSettingAddCommandHandler customerSettingAddCommandHandler;

    public CommandBusConfig(CommandBus commandBus, CustomerRegisterCommandHandler customerRegisterCommandHandler, CustomerProfileEditCommandHandler customerProfileEditCommandHandler, PostedBalanceCommandHandler postedBalanceCommandHandler, CustomerNotificationChannelConfigCommandHandler customerNotificationChannelConfigCommandHandler, CustomerSettingAddCommandHandler customerSettingAddCommandHandler) {
        this.commandBus = commandBus;
        this.customerRegisterCommandHandler = customerRegisterCommandHandler;
        this.customerProfileEditCommandHandler = customerProfileEditCommandHandler;
        this.postedBalanceCommandHandler = postedBalanceCommandHandler;
        this.customerNotificationConfigCommandHandler = customerNotificationChannelConfigCommandHandler;
        this.customerSettingAddCommandHandler = customerSettingAddCommandHandler;
    }

    @PostConstruct
    public void registerHandlers() {
        commandBus.registerHandler(CustomerRegisterCommand.class, customerRegisterCommandHandler);
        commandBus.registerHandler(CustomerProfileEditCommand.class, customerProfileEditCommandHandler);
        commandBus.registerHandler(PostedBalanceCommand.class, postedBalanceCommandHandler);
        commandBus.registerHandler(CustomerNotificationChannelConfigCommand.class, customerNotificationConfigCommandHandler);
        commandBus.registerHandler(CustomerSettingAddCommand.class, customerSettingAddCommandHandler);
    }
}
