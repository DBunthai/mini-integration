package mini.integration.customerservice.application.command.handler;


import mini.integration.customerservice.application.command.CustomerSettingAddCommand;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingAddDTO;
import mini.integration.customerservice.lib.CommandHandler;

public interface CustomerSettingAddCommandHandler extends CommandHandler<CustomerSettingAddCommand, CustomerSettingAddDTO> {
}
